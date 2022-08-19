package oasip.backend.DTOs.Category;

import lombok.Data;
import oasip.backend.Validation.Category.CategoryUniqueName;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class CategoryEditDto implements Serializable {

    private Integer id;
    @NotEmpty(message = "EventCategoryName must be not empty")
    @NotNull(message = "EventCategoryName must be not null")
    @Size(max = 100, message = "EventCategoryName size must be between 1 and 100")
    @CategoryUniqueName(message = "The eventCategoryName is NOT unique.")
    private String eventCategoryName;

    @Size(max = 500, message = "EventCategoryDescription size must be between 0 and 500")
    private String eventCategoryDescription;

    @NotNull(message = "Duration must be not null")
    @Max(value = 480 , message = "Duration must be between 1 and 480.")
    @Min(value = 1 , message = "Duration must be between 1 and 480.")
    private Integer eventCategoryDuration;
}
