package pl.coderslab.charity;

import org.hibernate.annotations.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.user.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid User user, BindingResult result, @RequestParam String password2, HttpSession session) {
        User existingUser = userRepository.findFirstByEmail(user.getEmail());
        if (existingUser != null) {
            result.addError(new FieldError("user", "email", "User with this email already exists"));
        }
        if (!user.getPassword().equals(password2)){
            result.addError(new FieldError("user", "password", "Passwords not matching!"));
        }
        if (result.hasErrors()) {
            return "register";
        }
        User userToBeSaved = user;
        userToBeSaved.setPassword(hashPassword(user.getPassword()));
        userRepository.save(userToBeSaved);
        session.setAttribute("loggedUser", userToBeSaved);
        return "redirect:/app/donate";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.removeAttribute("message");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!userRepository.existsUserByEmail(email)) {
            session.setAttribute("message", "Invalid  login!");
            return "/login";
        }
        User user = userRepository.findFirstByEmail(email);
        if (!checkPassword(password, user.getPassword())) {
            session.setAttribute("message", "Invalid  password!");
            return "/login";
        }
        session.setAttribute("loggedUser", user);
        return "redirect:/app/donate";
    }
}
