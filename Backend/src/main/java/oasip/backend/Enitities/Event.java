package oasip.backend.Enitities;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId", nullable = false)
    private Integer id;

    @Column(name = "bookingName", nullable = false, length = 100)
    private String bookingName;

    @Column(name = "bookingEmail", nullable = false)
    private String bookingEmail;

    @Column(name = "eventStartTime", nullable = false)
    private Date eventStartTime;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @Lob
    @Column(name = "eventNotes")
    private String eventNotes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EventCategory_id", nullable = false)
    private Eventcategory eventCategory;

}