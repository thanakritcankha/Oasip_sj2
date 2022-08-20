package oasip.backend.DTOs.Category;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryListAllDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventCategoryDuration;
}
