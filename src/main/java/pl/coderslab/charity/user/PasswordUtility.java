package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

@AllArgsConstructor
public class PasswordUtility {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern COMPILED = Pattern.compile(PASSWORD_PATTERN);

    boolean verifyPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

}
