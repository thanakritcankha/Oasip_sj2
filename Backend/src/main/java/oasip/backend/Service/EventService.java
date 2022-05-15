package oasip.backend.Service;

import oasip.backend.DTOs.Create.CreateEventDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.EventDto;
import oasip.backend.DTOs.EventcategoryDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.DTOs.Overlap.OverlapEventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.ListMapper;
import oasip.backend.repositories.EventRepository;
import oasip.backend.repositories.EventcategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        System.out.println(events);
        return listMapper.maplist(events , OverlapEventDto.class , modelMapper);
    }

    public CreateEventDto createEvent(CreateEventDto newEvent){
        try {
            Event event = modelMapper.map(newEvent,Event.class);
            repository.saveAndFlush(event);
            return newEvent;
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "กรุณาใส่ข้อมูลให้ถูกต้อง" , ex);
        }
    }
    public void deleteEvent(Integer eventId){
        repository.findById(eventId).orElseThrow(
                () -> new RuntimeException(eventId + " Does not Exist !!!" ));
        repository.deleteById(eventId);
    }
    public EventDto updateEvent(EditEventDto updateEvent , Integer eventId){
        Event newEvent = modelMapper.map(updateEvent,Event.class);
        Event event = repository.findById(eventId).map(o -> mapEvent(o,newEvent)).orElseGet(() -> {
            newEvent.setId(eventId);
            return newEvent;
        });
        System.out.println(event);
        repository.saveAndFlush(event);
        return modelMapper.map(event , EventDto.class);
    }

private Event mapEvent(Event existingEvent, Event updateEvent) {
    existingEvent.setEventStartTime(updateEvent.getEventStartTime());
    existingEvent.setEventNotes(updateEvent.getEventNotes());
    existingEvent.setEventCategory(eventcategoryRepository.getById(existingEvent.getEventCategory().getId()));
    return existingEvent; }

}
