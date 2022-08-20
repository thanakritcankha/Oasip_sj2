package oasip.backend.DTOs.Event;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventEditDto implements Serializable {
    private Date eventStartTime;
    private String eventNotes;
}
