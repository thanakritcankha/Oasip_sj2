package oasip.backend.DTOs;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventCategoryDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventDuration;
}
