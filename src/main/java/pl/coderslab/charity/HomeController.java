package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;



@Controller
public class HomeController {
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final EmailServiceImpl emailSender;
    private String appInbox = "magicmike.developer@gmail.com";

    public HomeController(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository, EmailServiceImpl emailSender) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.emailSender = emailSender;
    }


    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("bags", donationRepository.findTotalAmountOfDonatedBags());
        model.addAttribute("donations", donationRepository.findTotalAmountOfDonations());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "index";
    }
    @RequestMapping("/contactForm")
    public String sendEmail(@RequestParam String name, @RequestParam String surname, @RequestParam String message) {
        String sender = name + " " + surname;
        emailSender.sendSimpleMessage(appInbox, "Message from" +sender, message);
        return "msg-confirmation";
    }

}
