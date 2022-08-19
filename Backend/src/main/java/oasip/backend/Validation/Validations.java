package oasip.backend.Validation;


import lombok.*;
import oasip.backend.DTOs.Event.EventListAllDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Validations {
    private String textError = "";

    public void overlab(List<EventListAllDto> overlap , Date startTime , Integer duration) {
        if(startTime != null){
            Date newStartTime = new Date(startTime.getTime());
            Date newEndTime =  new Date(startTime.getTime() + (duration * 60000));
            List<EventListAllDto> result = overlap.stream().filter((old) -> {
                Date oldDateTime = new Date(old.getEventStartTime().getTime());
                Date oldEndTime = new Date(old.getEventStartTime().getTime() + (old.getEventDuration() * 60000));

                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                String newstringDate= DateFor.format(startTime);
                String oldstringDate= DateFor.format(old.getEventStartTime());

                Date newdate = null;
                Date olddate = null;
                try {
                    newdate = DateFor.parse(newstringDate);
                    olddate = DateFor.parse(oldstringDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if ((olddate.compareTo(newdate) == 0) || (oldEndTime.compareTo(newdate) == 0)) {
                    if(((oldDateTime.compareTo(newStartTime) <= 0) && (newStartTime.compareTo(oldEndTime) < 0))
                            || ((oldDateTime.compareTo(newEndTime) < 0) && (newEndTime.compareTo(oldEndTime) <= 0))
                            || ((newStartTime.compareTo(oldDateTime) < 0) && (oldEndTime.compareTo(newEndTime) < 0))
                            || ((oldDateTime.compareTo(newStartTime) < 0) && (newEndTime.compareTo(oldEndTime) < 0))){
                        return true;
                    }
                    return false;
                }
                return false;
            }).collect(Collectors.toList());
            if (result.size() > 0){
                this.textError =  this.textError + "requested event overlapped with existing events; ";
            }
        }
    }
}
