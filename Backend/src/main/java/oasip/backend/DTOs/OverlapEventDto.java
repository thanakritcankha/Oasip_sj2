package oasip.backend.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OverlapEventDto implements Serializable {
    private Date eventStartTime;
    private Integer eventDuration;
}
