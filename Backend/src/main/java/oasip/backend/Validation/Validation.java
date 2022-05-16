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
        if (note.length() > 500){
            this.textError = this.textError + "note size must be between 0 and 500; ";
        }
    }
    public void overlab(List<OverlapEventDto> overlap , Date startTime , Integer duration) {
        Date newStartTime = new Date();
        newStartTime.setTime(startTime.getTime());
        Date plusDuration =  new Date();
        plusDuration.setTime(startTime.getTime());

//        System.out.println(startTime.getTime());
//        System.out.println(plusDuration);
        plusDuration.setTime(plusDuration.getTime() + (duration * 60000));
//        System.out.println("newdate \n"+startTime);
//        System.out.println("newdate and plus duration \n"+plusDuration);
//        System.out.println(overlap);
        List<OverlapEventDto> result = overlap.stream().filter((old) -> {
            Date oldDateTime = new Date();
            oldDateTime.setTime(old.getEventStartTime().getTime());
            Date oldplusDuration = new Date();
//            System.out.println("------------------------------\n");
//            System.out.println("oldDate \n"+oldplusDuration);
            oldplusDuration.setTime(old.getEventStartTime().getTime() + (old.getEventDuration() * 60000));
//            System.out.println("duration" + old.getEventDuration());
//            System.out.println("oldTime \n"+oldplusDuration);
//            System.out.println("------------------------------\n");
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
            if ((olddate.compareTo(newdate) == 0) || (oldplusDuration.compareTo(newdate) == 0)) {
//                System.out.println("------------------");
//                System.out.println("old not plus \n"+oldDateTime);
//                System.out.println("old plus \n"+oldplusDuration);
//                System.out.println("startTIme \n" + startTime);
//                System.out.println("------------------");
//                System.out.println(oldDateTime.after(newStartTime));
                if((oldDateTime.before(newStartTime) && oldplusDuration.after(newStartTime)) || (oldDateTime.before(plusDuration)&& oldplusDuration.after(plusDuration))){
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
