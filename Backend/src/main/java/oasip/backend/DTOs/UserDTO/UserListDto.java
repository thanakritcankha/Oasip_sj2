package oasip.backend.DTOs.UserDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserListDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String role;
}
