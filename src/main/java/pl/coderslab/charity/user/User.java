package pl.coderslab.charity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Please enter valid email address")
    @Email(message = "Please enter valid email address")
    @Column(unique = true)
    private String email;
    @Size(min = 3, message = "Password too short")
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
