package oasip.backend.DTOs.Category;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryListFilterDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
}
