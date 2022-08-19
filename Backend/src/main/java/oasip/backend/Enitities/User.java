package oasip.backend.Enitities;

import lombok.*;
import oasip.backend.Enum.Role;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @Column(name = "role", nullable = false, length = 45)
    private Role role;

    @Column(name = "createOn", nullable = false)
    private Instant createOn;

    @Column(name = "updateOn", nullable = false)
    private Instant updateOn;

    @OneToMany(mappedBy = "user")
    private Set<Event> events = new LinkedHashSet<>();


}