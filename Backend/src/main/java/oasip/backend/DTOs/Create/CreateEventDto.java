package oasip.backend.DTOs.Create;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class CreateEventDto implements Serializable {
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private CreateEventcategoryDto eventCategory;
}
