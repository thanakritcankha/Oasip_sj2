package oasip.backend.Service;

import oasip.backend.DTOs.Create.CreateEventDto;
import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.DTOs.Overlap.OverlapEventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.ListMapper;
import oasip.backend.Validation.Validations;
import oasip.backend.repositories.EventRepository;
import oasip.backend.repositories.EventcategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private EventcategoryRepository eventcategoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private EventCategoryService categoryService;

    public List<ListAllEventDto> getAllEvent(){
        List<Event> events = repository.findAll(Sort.by("eventStartTime").descending());
        return listMapper.maplist(events, ListAllEventDto.class,modelMapper);
    }
    public DetailEventDto getEvent(Integer eventId){
        Event event = repository.findById(eventId).orElseThrow(
                () -> new RuntimeException(eventId + " Does not Exist !!!" ));
        return modelMapper.map(event , DetailEventDto.class);
    }

    public List<OverlapEventDto> getOldEvent(Integer categoryId){
        List<Event> events = repository.findByEventCategory_Id(categoryId);
//        System.out.println(events);
        return listMapper.maplist(events , OverlapEventDto.class , modelMapper);
    }

    public CreateEventDto createEvent(CreateEventDto newEvent){

        Validations validation = new Validations();

        if(newEvent.getEventDuration() == null){
            if(newEvent.getEventCategoryId() != null){

                CreateEventcategoryDto eventcategory = categoryService.getCategory(newEvent.getEventCategoryId());
                newEvent.setEventDuration(eventcategory.getEventCategoryDuration());
                validation.overlab(getOldEvent(newEvent.getEventCategoryId()) , newEvent.getEventStartTime() , newEvent.getEventDuration());
            }
        }else{
            validation.overlab(getOldEvent(newEvent.getEventCategoryId()) , newEvent.getEventStartTime() , newEvent.getEventDuration());
        }
        if (validation.getTextError().length() > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,validation.getTextError());
        }


        Event event = modelMapper.map(lop(newEvent),Event.class);
//        System.out.println("------------------------------");
//        System.out.println(event);
//        System.out.println("------------------------------");
        repository.saveAndFlush(event);
        return newEvent;
    }
    private CreateEventDto lop(@Valid CreateEventDto event){
        return event;
    }

    public void deleteEvent(Integer eventId){
        repository.findById(eventId).orElseThrow(
                () -> new RuntimeException(eventId + " Does not Exist !!!" ));
        repository.deleteById(eventId);
    }
    public EditEventDto updateEvent(EditEventDto updateEvent , Integer eventId){
//        System.out.println(updateEvent);
        Event newEvent = modelMapper.map(updateEvent,Event.class);
        Event event = repository.findById(eventId).map(o -> mapEvent(o,newEvent)).orElseGet(() -> {
            newEvent.setId(eventId);
            return newEvent;
        });
//        System.out.println(event);
        repository.saveAndFlush(event);
        return modelMapper.map(event , EditEventDto.class);
    }

private Event mapEvent(Event existingEvent, Event updateEvent) {
        if (updateEvent.getEventStartTime() != null){
            existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        }
        if (updateEvent.getEventNotes() != null){
            existingEvent.setEventNotes(updateEvent.getEventNotes());
        }
//    System.out.println("--------------");
//    System.out.println(existingEvent);
//    existingEvent.setEventCategory(eventcategoryRepository.getById(existingEvent.getEventCategory().getId()));
    return existingEvent; }

}
