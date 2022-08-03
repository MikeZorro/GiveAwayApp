package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.user.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;
import java.util.regex.Pattern;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;


    public UserController(UserRepository userRepository, EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    boolean verifyPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
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
        if (!user.getPassword().equals(password2)) {
            result.addError(new FieldError("user", "password", "Passwords not matching!"));
        }
        if (!verifyPassword(user.getPassword())) {
            result.addError(new FieldError("user", "password", "Passwords must be at least 8 characters long, contain small and capital letters, at least 1 number and at least 1 special sign"));
        }
        if (result.hasErrors()) {
            return "register";
        }
        User userToBeSaved = user;
        UUID token = UUID.randomUUID();
        userToBeSaved.setPassword(hashPassword(user.getPassword()));
        userToBeSaved.setToken(String.valueOf(token));
        userRepository.save(userToBeSaved);
        emailService.sendSimpleMessage(user.getEmail(), "Link Aktywacyjny", "Dzien dobry, Twój link aktywacyjny to: localhost:8080/activate/"+token);
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String login(HttpSession session) {
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
        if (!user.getToken().equals("active")) {
            session.setAttribute("message", "Account not active, try checking your spam mailbox!");
            return "/login";
        }
        session.setAttribute("loggedUser", user);
        return "redirect:/app/donate";
    }

    @RequestMapping("/activate/{token}")
    public String activate(@PathVariable String token, HttpSession session) {
        User user = userRepository.findUserByToken(token);
        if (user == null) {
            return "register";
        }
        user.setToken("active");
        userRepository.save(user);
        session.setAttribute("message", "Account activated!");
        return "redirect:/login";
    }
}
