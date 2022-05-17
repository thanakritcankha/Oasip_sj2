package oasip.backend.DTOs.Edits;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ResPatchDto implements Serializable {
    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private String eventNotes;
    private String eventCategoryEventCategoryName;
}
