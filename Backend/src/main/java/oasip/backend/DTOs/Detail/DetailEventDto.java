package oasip.backend.DTOs.Detail;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class DetailEventDto implements Serializable {
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private DetailEventcategoryDto eventCategory;
}
