package oasip.backend.Enitities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "eventcategories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Eventcategory {
    @Id
    @Column(name = "eventCategoryId", nullable = false)
    private Integer id;

    @Column(name = "eventCategoryName", nullable = false, length = 100)
    private String eventCategoryName;

    @Lob
    @Column(name = "eventCategoryDescription")
    private String eventCategoryDescription;

    @Column(name = "eventCategoryDuration", nullable = false)
    private Integer eventCategoryDuration;

    @JsonIgnore
    @OneToMany(mappedBy = "eventCategory")
    private Set<Event> events = new LinkedHashSet<>();

}