package oasip.backend.DTOs.ListAll;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ListAllEventDto implements Serializable {
    private Integer id;
    private String bookingName;
//    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
//    private String eventNotes;
    private ListAllEventcategoryDto eventCategory;
}
