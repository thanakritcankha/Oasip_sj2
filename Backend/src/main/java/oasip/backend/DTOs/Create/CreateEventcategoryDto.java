package oasip.backend.DTOs.Create;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateEventcategoryDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventCategoryDuration;
}
