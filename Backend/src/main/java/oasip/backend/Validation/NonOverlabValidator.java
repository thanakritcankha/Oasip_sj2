package oasip.backend.Validation;

import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Create.ValidationCreateEventDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.Service.EventCategoryService;
import oasip.backend.Service.EventService;
import oasip.backend.repositories.EventcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NonOverlabValidator implements ConstraintValidator<NonOverlab, ValidationCreateEventDto> {
    @Autowired
    private EventCategoryService categoryService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventcategoryRepository categoryRepository;
    @Override
    public boolean isValid(ValidationCreateEventDto createEventDto, ConstraintValidatorContext constraintValidatorContext) {
        if(createEventDto.getEventDuration() == null){
            if(createEventDto.getEventCategoryId() != null){
//                Eventcategory eventcategory = categoryRepository.findById(createEventDto.getEventCategoryId())
                CreateEventcategoryDto eventcategory = categoryService.getCategory(createEventDto.getEventCategoryId());
                createEventDto.setEventDuration(eventcategory.getEventCategoryDuration());
                return !(overlab(eventService.getEachEventCategories(createEventDto.getEventCategoryId()), createEventDto.getEventStartTime(), createEventDto.getEventDuration()));
            }
            return true;
        }else{
            return !(overlab(eventService.getEachEventCategories(createEventDto.getEventCategoryId()), createEventDto.getEventStartTime(), createEventDto.getEventDuration()));
        }
    }

    public Boolean overlab(List<ListAllEventDto> overlap , Date startTime , Integer duration) {
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
//                this.textError =  this.textError + "requested event overlapped with existing events; ";
                return true;
            }
        }
        return false;
    }
}
