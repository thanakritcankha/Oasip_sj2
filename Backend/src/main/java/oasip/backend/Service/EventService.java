package oasip.backend.Service;

import oasip.backend.DTOs.Event.EventCreateDto;
import oasip.backend.DTOs.Event.EventDetailDto;
import oasip.backend.DTOs.Event.EventEditDto;
import oasip.backend.DTOs.Event.EventListAllDto;
import oasip.backend.Enitities.Event;
import oasip.backend.ListMapper;
import oasip.backend.repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private EventCategoryService categoryService;

    public List<EventListAllDto> getAllEvent() {
        List<Event> events = eventRepository.findAll(Sort.by("eventStartTime").descending());
        return listMapper.maplist(events, EventListAllDto.class, modelMapper);
    }

    public EventDetailDto getEvent(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , eventId + " Does not Exist !!!"));
        return modelMapper.map(event, EventDetailDto.class);
    }

    public List<EventListAllDto> filterEvents(Integer categoryId , Integer optionId , Date time) {
        if (categoryId == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "categoryId is not null");
        }
        List<Event> events = null;
        if (categoryId != 0) {
            events = eventRepository.findByEventCategory_Id(categoryId, Sort.by("eventStartTime").descending());
        } else {
            events = eventRepository.findAll(Sort.by("eventStartTime").descending());
        }
        List<EventListAllDto> newEvent = listMapper.maplist(events, EventListAllDto.class, modelMapper);
        List<EventListAllDto> filteroption = null;
        switch (optionId) {
            case 1: //Upcoming
                filteroption = getUpcoming(newEvent);
                break;
            case 2: //Past
                filteroption = getPast(newEvent);
                break;
            case 3: // Start Time
                filteroption = getDay(newEvent, time);
                break;
            default:
                filteroption = newEvent;
                break;
        }
        return filteroption;
    }

    public List<EventListAllDto> getEachEventCategories(Integer categoryId) {
        List<Event> events = eventRepository.findByEventCategory_Id(categoryId, Sort.by("eventStartTime").descending());
        return listMapper.maplist(events, EventListAllDto.class, modelMapper);
    }

    public List<EventListAllDto> getUpcoming(List<EventListAllDto> event) {
        List<EventListAllDto> eventDtos = listMapper.maplist(event, EventListAllDto.class, modelMapper);
        Date date = new Date();
        List<EventListAllDto> events = eventDtos.stream().filter((value) -> {
            Date valueEnd = new Date();
            valueEnd.setTime((value.getEventStartTime().getTime() + (value.getEventDuration() * 60000)));
            return ((value.getEventStartTime().compareTo(date) > 0) || (value.getEventStartTime().compareTo(valueEnd) > 0));
        }).collect(Collectors.toList());
        Collections.reverse(events);
        return listMapper.maplist(events, EventListAllDto.class, modelMapper);
    }

    public List<EventListAllDto> getPast(List<EventListAllDto> event) {
        Date date = new Date();
        List<EventListAllDto> events = event.stream().filter((value) -> {
            return (value.getEventStartTime().compareTo(date) < 0);
        }).collect(Collectors.toList());
        return listMapper.maplist(events, EventListAllDto.class, modelMapper);
    }

    public List<EventListAllDto> getDay(List<EventListAllDto> event, Date selectday) {
        if (selectday == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date is not null ");
        }
        Date date = new Date();
        date.setTime(selectday.getTime());
        Date end = new Date();
        end.setTime(date.getTime() + 86400000);
        List<EventListAllDto> events = event.stream().filter((value) -> {
            return ((value.getEventStartTime().compareTo(date) > 0) && (value.getEventStartTime().compareTo(end) < 0));
        }).collect(Collectors.toList());
        Collections.reverse(events);
        return listMapper.maplist(events, EventListAllDto.class, modelMapper);
    }

    public EventCreateDto createEvent(EventCreateDto newEvent) {
//        Validations validation = new Validations();
//        if (newEvent.getEventDuration() == null) {
//            if (newEvent.getEventCategoryId() != null) {
//                CreateEventcategoryDto eventcategory = categoryService.getCategory(newEvent.getEventCategoryId());
//                newEvent.setEventDuration(eventcategory.getEventCategoryDuration());
//                validation.overlab(getEachEventCategories(newEvent.getEventCategoryId()), newEvent.getEventStartTime(), newEvent.getEventDuration());
//            }
//        } else {
//            validation.overlab(getEachEventCategories(newEvent.getEventCategoryId()), newEvent.getEventStartTime(), newEvent.getEventDuration());
//        }
//        if (validation.getTextError().length() > 0) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validation.getTextError());
//        }
        Event event = modelMapper.map(newEvent, Event.class);
        eventRepository.saveAndFlush(event);
        return newEvent;
    }

    public void deleteEvent(Integer eventId) {
        eventRepository.findById(eventId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , eventId + " Does not Exist !!!"));
        eventRepository.deleteById(eventId);
    }

    public EventEditDto updateEvent(EventEditDto updateEvent, Integer eventId) {
        Event newEvent = modelMapper.map(updateEvent, Event.class);
        Event event = eventRepository.findById(eventId).map(o -> mapEvent(o, newEvent)).orElseGet(() -> {
            newEvent.setId(eventId);
            return newEvent;
        });
        eventRepository.saveAndFlush(event);
        return modelMapper.map(event, EventEditDto.class);
    }

    private Event mapEvent(Event existingEvent, Event updateEvent) {
        if (updateEvent.getEventStartTime() != null) {
            existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        }
        if (updateEvent.getEventNotes() != null) {
            existingEvent.setEventNotes(updateEvent.getEventNotes());
        }
        return existingEvent;
    }

}
