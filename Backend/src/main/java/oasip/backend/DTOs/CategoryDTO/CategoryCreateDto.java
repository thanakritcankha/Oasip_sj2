package oasip.backend.DTOs.CategoryDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryCreateDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventCategoryDuration;
}
