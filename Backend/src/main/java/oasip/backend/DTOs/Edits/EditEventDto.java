package oasip.backend.DTOs.Edits;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EditEventDto implements Serializable {
    private Integer id;
    private Date eventStartTime;
    private String eventNotes;
}
