<<<<<<< HEAD:Backend/src/main/java/oasip/backend/DTOs/CategoryDTO/CategoryCreateDto.java
package oasip.backend.DTOs.CategoryDTO;
=======
package oasip.backend.DTOs.Category;
>>>>>>> dev044:Backend/src/main/java/oasip/backend/DTOs/Category/CategoryCreateDto.java

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryCreateDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventCategoryDuration;
}
