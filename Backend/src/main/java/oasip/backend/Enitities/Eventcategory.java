package oasip.backend.Enitities;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "eventcategory")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Eventcategory {
    @Id
    @Column(name = "eventCatedoryId", nullable = false)
    private Integer id;

    @Column(name = "eventCategoryName", nullable = false, length = 100)
    private String eventCategoryName;

    @Lob
    @Column(name = "eventCategoryDescription")
    private String eventCategoryDescription;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @OneToMany(mappedBy = "eventCategory")
    private Set<Event> events = new LinkedHashSet<>();

}