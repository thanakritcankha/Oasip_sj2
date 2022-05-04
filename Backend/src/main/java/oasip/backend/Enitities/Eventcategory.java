package oasip.backend.Enitities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "eventcategories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Eventcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventCategoryId", nullable = false)
    private Integer id;

    @Column(name = "eventCategoryName", nullable = false, length = 100)
    private String eventCategoryName;

    @Lob
    @Column(name = "eventCategoryDescription")
    private String eventCategoryDescription;

    @Column(name = "eventCategoryDuration", nullable = false)
    private Integer eventCategoryDuration;

}