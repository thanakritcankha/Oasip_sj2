package oasip.backend.DTOs.Overlap;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OverlapEventDto implements Serializable {
    private Integer id;
    private Date eventStartTime;
    private Integer eventDuration;
    private OverlapEventcategoryDto eventCategory;
}
