package oasip.backend.DTOs.Filter;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FilterDto implements Serializable {
    private Integer eventCategoryId;
    private Integer option;
    private Date eventStartTime;
}
