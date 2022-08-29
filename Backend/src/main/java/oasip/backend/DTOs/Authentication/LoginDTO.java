package oasip.backend.DTOs.Authentication;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    @NotNull(message = "Email must be not null.")
    @NotEmpty(message = "Email must be not Empty.")
//    @Size(min = 1, max = 50, message = "Email size must be between 1 and 50.")
//    @Email(message = "Email must be a well-formed email address",
//            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
//                    "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\" +
//                    "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[" +
//                    "a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0" +
//                    "-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-" +
//                    "z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x" +
//                    "7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

//    @NotNull(message = "Password must be not null.")
//    @Size(min = 8, max = 14, message = "Password size must be between 8 and 14.")
    @NotEmpty(message = "Password must be not Empty.")
    private String password;
}
