package oasip.backend.DTOs.Create;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CreateDto implements Serializable {
    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private Integer eventCategoryId;
    private String eventCategoryEventCategoryName;
}
