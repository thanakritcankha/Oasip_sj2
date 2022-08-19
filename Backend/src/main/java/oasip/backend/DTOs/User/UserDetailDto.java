package oasip.backend.DTOs.User;

import lombok.Data;
import oasip.backend.Enum.Role;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UserDetailDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private Instant createOn;
    private Instant updateOn;
}
