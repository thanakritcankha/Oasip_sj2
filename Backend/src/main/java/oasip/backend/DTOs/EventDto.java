package oasip.backend.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventDto implements Serializable {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private EventcategoryDto eventCategory;
}
