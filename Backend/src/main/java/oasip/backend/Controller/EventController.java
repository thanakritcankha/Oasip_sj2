package oasip.backend.Controller;

import oasip.backend.DTOs.EventCategoryDto;
import oasip.backend.DTOs.EventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.Enitities.EventCategory;
import oasip.backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping("")
    public List<EventDto> getAllEvent(){
        return service.getAllEvent();
    }

    @GetMapping("/{eventId}")
    public EventDto getEvent(@PathVariable Integer eventId){
        return service.getEvent(eventId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@RequestBody EventDto newEvent){
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable Integer eventId){
        service.deleteEvent(eventId);
    }

    @PutMapping("/{eventId}")
    public Event update(@PathVariable Integer eventId , @RequestBody EventDto updateEvent){
        return service.updateEvent(updateEvent , eventId);
    }
}
