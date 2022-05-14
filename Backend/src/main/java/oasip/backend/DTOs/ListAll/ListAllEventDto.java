package oasip.backend.DTOs.ListAll;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class ListAllEventDto implements Serializable {
    private Integer id;
    private String bookingName;
//    private String bookingEmail;
    private Date eventStartTime;
    private Integer eventDuration;
//    private String eventNotes;
    private ListAllEventcategoryDto eventCategory;
}
