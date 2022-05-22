package oasip.backend.Service;

import oasip.backend.DTOs.Create.CreateEventDto;
import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.Filter.FilterDto;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
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

    public List<ListAllEventDto> getFilter(FilterDto filter){
        List<Event> events = null;
        if (filter.getEventCategoryId() != 0){
            events  = repository.findByEventCategory_Id(filter.getEventCategoryId(), Sort.by("eventStartTime").descending());
        }else {
            events = repository.findAll(Sort.by("eventStartTime").descending());
        }
        List<ListAllEventDto> newEvent = listMapper.maplist(events , ListAllEventDto.class , modelMapper);
        List<ListAllEventDto> filteroption = null;
        switch (filter.getOption()){
            case 1: //Upcoming
                filteroption = getUpcoming(newEvent);
                break;
            case 2: //Past
                filteroption = getPast(newEvent);
                break;
            case 3: // Start Time
                filteroption = getDay(newEvent , filter.getEventStartTime());
                break;
            default:
                filteroption = newEvent;
                break;
        }

        return  filteroption;
    }



    public List<ListAllEventDto> getOldEvent(Integer categoryId){
        List<Event> events = repository.findByEventCategory_Id(categoryId , Sort.by("eventStartTime").descending());
//        System.out.println(events);
        return listMapper.maplist(events , ListAllEventDto.class , modelMapper);
    }

    public List<ListAllEventDto> getUpcoming(List<ListAllEventDto> event){
        List<ListAllEventDto> eventDtos = listMapper.maplist(event , ListAllEventDto.class , modelMapper);
        Date date = new Date();
        List<ListAllEventDto> events = eventDtos.stream().filter((value)->{
            Date valueEnd = new Date();
            valueEnd.setTime((value.getEventStartTime().getTime() + (value.getEventDuration() * 60000)));
            System.out.println((value.getEventStartTime().compareTo(date) > 0) || (value.getEventStartTime().compareTo(valueEnd) > 0));
            return  ((value.getEventStartTime().compareTo(date) > 0) || (value.getEventStartTime().compareTo(valueEnd) > 0));
        }).collect(Collectors.toList());
        Collections.reverse(events);
        System.out.println(events);
        return listMapper.maplist(events , ListAllEventDto.class , modelMapper);
    }

    public List<ListAllEventDto> getPast(List<ListAllEventDto> event){
        Date date = new Date();
        List<ListAllEventDto> events = event.stream().filter((value)->{
            return  (value.getEventStartTime().compareTo(date) < 0);
        }).collect(Collectors.toList());

//        System.out.println(events);
        return listMapper.maplist(events , ListAllEventDto.class , modelMapper);
    }

    public List<ListAllEventDto> getDay(List<ListAllEventDto> event,Date selectday){
        Date date = new Date();
        date.setTime(selectday.getTime());
        Instant inst = date.toInstant();
        LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
        Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date day = Date.from(dayInst);
        Date end = new Date();
        end.setTime(day.getTime() + 86400000);
//        System.out.println("start \n" + day);
//        System.out.println("end \n" + end);
        List<ListAllEventDto> events = event.stream().filter((value)->{
            System.out.println(((value.getEventStartTime().compareTo(day) > 0) && (value.getEventStartTime().compareTo(end) < 0)));
            return  ((value.getEventStartTime().compareTo(day) > 0) && (value.getEventStartTime().compareTo(end) < 0));
        }).collect(Collectors.toList());
        Collections.reverse(events);
//        System.out.println(events);
        return listMapper.maplist(events , ListAllEventDto.class , modelMapper);
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
