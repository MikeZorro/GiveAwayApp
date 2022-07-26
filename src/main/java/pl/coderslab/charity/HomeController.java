package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;


@Controller
public class HomeController {
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    public HomeController(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("bags", donationRepository.findTotalAmountOfDonatedBags());
        model.addAttribute("donations", donationRepository.findTotalAmountOfDonations());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "index";
    }
}
