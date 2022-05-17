package oasip.backend.DTOs.Edits;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EditEventDto implements Serializable {
    private Date eventStartTime;
    private String eventNotes;
}
