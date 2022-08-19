package oasip.backend.DTOs.CategoryDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryListAllDto implements Serializable {
//    private Integer id;
    private String eventCategoryName;
}
