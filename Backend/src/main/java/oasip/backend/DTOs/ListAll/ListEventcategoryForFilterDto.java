package oasip.backend.DTOs.ListAll;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListEventcategoryForFilterDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
}
