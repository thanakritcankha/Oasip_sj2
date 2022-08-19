package oasip.backend.DTOs.User;

import lombok.Data;
import oasip.backend.Enum.Role;

import java.io.Serializable;

@Data
public class UserListAllDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Role role;
}
