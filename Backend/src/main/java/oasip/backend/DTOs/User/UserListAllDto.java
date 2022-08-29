package oasip.backend.DTOs.User;

import lombok.Data;
import oasip.backend.Enum.UserRole;

import java.io.Serializable;

@Data
public class UserListAllDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
