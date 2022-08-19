package oasip.backend.DTOs.EventDTO;

import lombok.Data;
import oasip.backend.DTOs.CategoryDTO.CategoryDetailDto;

import java.io.Serializable;
import java.time.Instant;

@Data
public class EventDetailDto implements Serializable {
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private CategoryDetailDto eventCategory;
}
