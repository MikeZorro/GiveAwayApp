package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app")
public class DonationController {
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }

    @ModelAttribute("categoryList")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    @RequestMapping(value = "/donate", method = RequestMethod.GET)
    public String donateAction(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }


    @RequestMapping(value = "/donate", method = RequestMethod.POST)
    public String donateActionSave(@Valid Donation donation, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/form";
        }
        HttpSession session = request.getSession();
        session.setAttribute("donation", donation);
        return "redirect:/app/donate/confirmation";
    }

    @RequestMapping(value = "/donate/confirmation", method = RequestMethod.GET)
    public String donateActionConfirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Donation donation = (Donation) session.getAttribute("donation");
        model.addAttribute("donation", donation);
        return "confirmation";
    }

    @RequestMapping(value = "/donate/confirmation", method = RequestMethod.POST)
    public String donateActionFinal( HttpServletRequest request) {
        HttpSession session = request.getSession();
        Donation donation = (Donation) session.getAttribute("donation");
        donationRepository.save(donation);
        return "donationfinal";
    }

}
