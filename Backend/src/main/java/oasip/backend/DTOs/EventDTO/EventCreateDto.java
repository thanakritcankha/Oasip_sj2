package oasip.backend.DTOs.EventDTO;

import lombok.Data;
import oasip.backend.DTOs.CategoryDTO.CategoryCreateDto;

import java.io.Serializable;
import java.time.Instant;

@Data
public class EventCreateDto implements Serializable {
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private CategoryCreateDto eventCategory;
}
