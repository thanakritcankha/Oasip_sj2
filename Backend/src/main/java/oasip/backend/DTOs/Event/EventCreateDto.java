package oasip.backend.DTOs.Event;

import lombok.Data;
import oasip.backend.Validation.Event.EventNonOverlab;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EventNonOverlab(message = "Requested event overlapped with existing events;")
public class EventCreateDto implements Serializable {

    @NotNull(message = "BookingName must be not null")
    @Size(min = 1, max = 100, message = "BookingName size must be between 1 and 100")
    private String bookingName;

    @NotNull(message = "BookingEmail must be not null")
    @Size(min = 1, max = 100, message = "BookingEmail size must be between 1 and 100")
    @Email(message = "Email must be a well-formed email address",
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
                    "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\" +
                    "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[" +
                    "a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0" +
                    "-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-" +
                    "z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x" +
                    "7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String bookingEmail;

    @NotNull(message = "EventStartTime must be not null")
    @Future(message = "EventStartTime must be a future date/time")
    private Date eventStartTime;

    private Integer eventDuration;

    @Size(max = 500, message = "EventNotes size must be between 0 and 500")
    private String eventNotes;

    @NotNull(message = "EventCategoryId must be not null")
    private Integer eventCategoryId;

}
