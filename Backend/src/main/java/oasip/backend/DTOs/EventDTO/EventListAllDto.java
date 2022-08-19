package oasip.backend.DTOs.EventDTO;

import lombok.Data;
import oasip.backend.DTOs.CategoryDTO.CategoryListAllDto;

import java.io.Serializable;
import java.time.Instant;

@Data
public class EventListAllDto implements Serializable {
    private Integer id;
    private String bookingName;
    private Instant eventStartTime;
    private Integer eventDuration;
    private CategoryListAllDto eventCategory;
}
