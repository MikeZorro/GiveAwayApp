package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

import java.util.List;

@Controller
public class TestController {
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    public TestController(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        institutionRepository.save(new Institution("Dbam o Zdrowie", "Pomoc dzieciom z ubogich rodzin."));
        institutionRepository.save(new Institution("A kogo", "Pomoc wybudzaniu dzieci ze śpiączki."));
        institutionRepository.save(new Institution("Dla dzieci", "Pomoc osobom znajdującym się w trudnej sytuacji życiowej."));
        institutionRepository.save(new Institution("Bez domu", "Pomoc dla osób nie posiadających miejsca zamieszkania"));
        categoryRepository.save(new Category("ubrania"));
        categoryRepository.save(new Category("zabawki"));
        categoryRepository.save(new Category("jedzenie"));
        categoryRepository.save(new Category("ksiazki"));
        categoryRepository.save(new Category("inne"));
        return "Its working!";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        List<Institution> list = institutionRepository.findAll();
        return list.toString();
    }

}
