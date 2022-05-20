package oasip.backend.DTOs.ListAll;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ListAllEventDto implements Serializable {
    private Integer id;
    private String bookingName;
    private Date eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private Integer eventCategoryId;
    private String eventCategoryEventCategoryName;
}
