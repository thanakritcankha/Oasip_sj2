package oasip.backend.Validation;


import lombok.*;
import oasip.backend.DTOs.Overlap.OverlapEventDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Validation {
    private String textError = "";

    public void Email(String email){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!Pattern.compile(regexPattern).matcher(email).matches()){
            this.textError = "email must be a well-formed email address; ";
        }
    }

    public void DateFuture(Date date ){
        Date now = new Date();
//        System.out.println("now \n"+now);
        if(now.after(date)){
            this.textError = this.textError + "event start time must be a future date/time; ";
        }
    }

    public void stringLength(String name , String email , String note){
        if (!(0 < name.length() && name.length() <= 100)){
            this.textError = this.textError + "name size must be between 1 and 100; ";
        }
        if (!(0 < email.length() && email.length() <= 100)){
            this.textError = this.textError + "email size must be between 1 and 100; ";
        }
        if (note != null){
            if (note.length() > 500){
                this.textError = this.textError + "note size must be between 0 and 500; ";
            }
        }
    }
    public void overlab(List<OverlapEventDto> overlap , Date startTime , Integer duration) {
//      Date newStartTime = new Date();
//      newStartTime.setTime(startTime.getTime());
//      Date newEndTime =  new Date();
//      newEndTime.setTime(startTime.getTime() + (duration * 60000));
        Date newStartTime = new Date(startTime.getTime());
        Date newEndTime =  new Date(startTime.getTime() + (duration * 60000));

        List<OverlapEventDto> result = overlap.stream().filter((old) -> {
//            Date oldDateTime = new Date();
//            oldDateTime.setTime(old.getEventStartTime().getTime());
//            Date oldEndTime = new Date();
//            oldEndTime.setTime(old.getEventStartTime().getTime() + (old.getEventDuration() * 60000));
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

//            System.out.println("new format \n" +newdate );
//            System.out.println("old format \n" +olddate );
//            if (id != old.getId())
            if ((olddate.compareTo(newdate) == 0) || (oldEndTime.compareTo(newdate) == 0)) {
//                System.out.println("------------------");
//                System.out.println(oldDateTime.compareTo(newStartTime));
//                System.out.println("oldstart \n"+oldDateTime);
//                System.out.println("new start \n" + newStartTime);
//                System.out.println(old);
//                System.out.println("------------------");
//                System.out.println(oldDateTime.after(newStartTime));
//                if((oldDateTime.before(newStartTime) && oldplusDuration.after(newStartTime)) || (oldDateTime.before(plusDuration)&& oldplusDuration.after(plusDuration))){
                if(((oldDateTime.compareTo(newStartTime) <= 0) && (newStartTime.compareTo(oldEndTime) < 0)) || ((oldDateTime.compareTo(newEndTime) < 0) && (newEndTime.compareTo(oldEndTime) <= 0))
                    || ((newStartTime.compareTo(oldDateTime) < 0) && (oldEndTime.compareTo(newEndTime) < 0)) || ((oldDateTime.compareTo(newStartTime) < 0) && (newEndTime.compareTo(oldEndTime) < 0))){
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
