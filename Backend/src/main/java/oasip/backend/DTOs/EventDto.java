package oasip.backend.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EventDto implements Serializable {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private LocalDateTime eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private EventcategoryDto eventCategory;
}
