package oasip.backend.Validation;


import lombok.*;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.DTOs.Overlap.OverlapEventDto;
import oasip.backend.Enitities.Eventcategory;

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
public class Validations {
    private String textError = "";

    public void uniqueName(String newName , List<Eventcategory> eventcategoryList){
        if (newName != null){
            List<Eventcategory> newcategory = eventcategoryList.stream().filter((valus)-> {
                return (valus.getEventCategoryName().toLowerCase() == newName.trim().toLowerCase());
            }).collect(Collectors.toList());
            if (newcategory.size() > 0){
                this.textError = this.textError + "The eventCategoryName is NOT unique.";
            }
        }else {
            this.textError = this.textError + "CategoryName must not null";
        }
    }
    public void duration(Integer newuration){
        if (newuration != null){
            if (!(newuration > 0 && newuration < 481)){
                this.textError =  this.textError + "Duration must ne between 1 and 480 ; The event duration is out of range.";
            }
        }else {
            this.textError = this.textError + "Duration must not null";
        }
    }


//    public void Email(String email){
//        if(email != null){
//            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
//            if(!Pattern.compile(regexPattern).matcher(email).matches()){
//                this.textError =  this.textError + "email must be a well-formed email address; ";
//            }
//        }
//    }
//
//    public void DateFuture(Date date ){
//        if (date != null){
//            Date now = new Date();
////        System.out.println("now \n"+now);
//            if(now.after(date)){
//                this.textError = this.textError + "event start time must be a future date/time; ";
//            }
//        }else {
//            this.textError = this.textError + "eventStartTime must be not null; ";
//        }
//    }
//
//    public void stringLength(String name , String email , String note){
////        System.out.println(name.length());
////        System.out.println(email.length());
////        System.out.println(note.length());
////        System.out.println(!(0 < name.length() && name.length() <= 100));
//        if (name != null){
//            if (!(0 < name.length() && name.length() <= 100)){
//                this.textError = this.textError + "name size must be between 1 and 100; ";
//            }
//        }else {
//            this.textError = this.textError + "name must be not null; ";
//        }
//        if(email != null){
//            if (!(0 < email.length() && email.length() <= 100)){
//                this.textError = this.textError + "email size must be between 1 and 100; ";
//            }
//        }else {
//            this.textError = this.textError + "email must be not null; ";
//        }
//        if (note != null){
//            if (note.length() > 500){
//                this.textError = this.textError + "note size must be between 0 and 500; ";
//            }
//        }
//    }
    public void overlab(List<ListAllEventDto> overlap , Date startTime , Integer duration) {
        if(startTime != null){
            Date newStartTime = new Date(startTime.getTime());
            Date newEndTime =  new Date(startTime.getTime() + (duration * 60000));
            List<ListAllEventDto> result = overlap.stream().filter((old) -> {
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
