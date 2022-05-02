package oasip.backend.Service;

import oasip.backend.DTOs.EventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.ListMapper;
import oasip.backend.repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<EventDto> getAllEvent(){
        List<Event> events = repository.findAll();
        return listMapper.maplist(events,EventDto.class,modelMapper);
    }
    public EventDto getEvent(Integer eventId){
        Event event = repository.findById(eventId).orElseThrow(
                () -> new RuntimeException(eventId + " Does not Exist !!!" ));
        return modelMapper.map(event , EventDto.class);
    }

    public EventDto createEvent(EventDto newEvent){
        Event event = modelMapper.map(newEvent,Event.class);
        //event category
        repository.saveAndFlush(event);
        return newEvent;
    }
    public void deleteEvent(Integer eventId){
        repository.findById(eventId).orElseThrow(
                () -> new RuntimeException(eventId + " Does not Exist !!!" ));
        repository.deleteById(eventId);
    }
    public Event updateEvent(EventDto updateEvent , Integer eventId){
        Event newEvent = modelMapper.map(updateEvent,Event.class);
        Event event = repository.findById(eventId).map(o -> mapEvent(o,newEvent)).orElseGet(() -> {
            newEvent.setId(eventId);
            return newEvent;
        });
        return repository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent, Event updateEvent) {
        existingEvent.setBookingName(updateEvent.getBookingName());
        existingEvent.setBookingEmail(updateEvent.getBookingEmail());
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        existingEvent.setEventDuration(updateEvent.getEventDuration());
        existingEvent.setEventNotes(updateEvent.getEventNotes());
        existingEvent.setEventCategory(updateEvent.getEventCategory());
        return existingEvent; }
}
