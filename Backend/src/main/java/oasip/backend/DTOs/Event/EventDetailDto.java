package oasip.backend.DTOs.Event;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventDetailDto implements Serializable {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private Integer eventCategoryId;
    private String eventCategoryEventCategoryName;
}
