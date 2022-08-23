package oasip.backend.DTOs.User;

import lombok.Data;
import oasip.backend.Enum.UserRole;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Data
public class UserDetailDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private UserRole role;
    private Timestamp createOn;
    private Timestamp updateOn;
}
