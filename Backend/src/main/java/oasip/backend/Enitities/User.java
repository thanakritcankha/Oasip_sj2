package oasip.backend.Enitities;

import lombok.*;
<<<<<<< HEAD

import javax.persistence.*;
import java.time.Instant;
=======
import oasip.backend.Enum.UserRole;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
>>>>>>> dev044

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

<<<<<<< HEAD
    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @Column(name = "createOn", nullable = false)
    private Instant createOn;

    @Column(name = "updateOn", nullable = false)
    private Instant updateOn;

=======
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "createOn", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createOn;

    @Column(name = "updateOn", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updateOn;

    @OneToMany(mappedBy = "user")
    private Set<Event> events = new LinkedHashSet<>();


>>>>>>> dev044
}