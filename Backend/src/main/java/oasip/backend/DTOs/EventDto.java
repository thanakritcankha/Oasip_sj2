package oasip.backend.DTOs;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class EventDto implements Serializable {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private EventcategoryDto eventCategory;
}
