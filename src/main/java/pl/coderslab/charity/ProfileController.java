package pl.coderslab.charity;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/app/profile")
public class ProfileController {
    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    boolean verifyPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewProfile(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "profile-details";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "profile-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProfileSave(@Valid User user, BindingResult result, HttpSession session) {
        User existingUser = userRepository.findFirstByEmail(user.getEmail());
        if (result.hasErrors()) {
            return "/profile-edit";
        }
        User updatedUser = user;
        updatedUser.setPassword(existingUser.getPassword());
        updatedUser.setToken(existingUser.getToken());
        userRepository.save(updatedUser);
        session.setAttribute("loggedUser", updatedUser);
        return "redirect:/app/profile/view";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String editPassword( HttpSession session) {
        session.removeAttribute("errorMsg");
        return "profile-password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String editPasswordSave(@RequestParam String password, @RequestParam String password2, HttpSession session) {
        User userToBeUpdated = (User) session.getAttribute("loggedUser");
        if (!password.equals(password2)){
            session.setAttribute("errorMsg", "Passwords not matching!");
            return "profile-password";
        }
        if (password.length() <3){
            session.setAttribute("errorMsg", "Passwords too short!");
            return "profile-password";
        }
        if (!verifyPassword(password)) {
            session.setAttribute("errorMsg", "Passwords must be at least 8 characters long, contain small and capital letters, at least 1 number and at least 1 special sign");
            return "profile-password";
        }
        session.removeAttribute("errorMsg");
        userToBeUpdated.setPassword(hashPassword(password));
        userRepository.save(userToBeUpdated);
        session.removeAttribute("loggedUser");
        return "redirect:/login";
    }
}
