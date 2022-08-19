package oasip.backend.DTOs.UserDTO;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UserDetailDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private Instant createOn;
    private Instant updateOn;
}
