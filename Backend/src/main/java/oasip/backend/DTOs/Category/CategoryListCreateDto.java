package oasip.backend.DTOs.Category;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryListCreateDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private Integer eventCategoryDuration;
}
