package oasip.backend.DTOs.Create;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListEventcategoryDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private Integer eventCategoryDuration;
}
