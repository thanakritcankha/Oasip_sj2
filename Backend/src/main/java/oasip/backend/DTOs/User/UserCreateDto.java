package oasip.backend.DTOs.User;

import lombok.Data;
import oasip.backend.Validation.User.UserCheckRole;
import oasip.backend.Validation.User.UserUniqueEmail;
import oasip.backend.Validation.User.UserUniqueName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserCreateDto implements Serializable {

    @UserUniqueName(message = "The Name must be unique.")
    @NotNull(message = "Name must be not null.")
    @NotEmpty(message = "Name must be not Empty.")
    @Size(min = 1, max = 100, message = "name size must be between 1 and 100.")
    private String name;

    @UserUniqueEmail(fieldId = "0",message = "The Email must be unique.")
    @NotNull(message = "Email must be not null.")
    @NotEmpty(message = "Email must be not Empty.")
    @Size(min = 1, max = 50, message = "Email size must be between 1 and 50.")
    @Email(message = "Email must be a well-formed email address",
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
                    "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\" +
                    "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[" +
                    "a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0" +
                    "-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-" +
                    "z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x" +
                    "7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;
    @Size(min = 8, max = 14, message = "Password size must be between 8 and 14.")
    private String password;

    @NotNull(message = "The Role must be admin, lecturer, or student.")
    @UserCheckRole(message = "The Role must be admin, lecturer, or student")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role.trim();
    }
}
