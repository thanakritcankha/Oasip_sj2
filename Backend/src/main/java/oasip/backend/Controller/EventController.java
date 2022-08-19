package oasip.backend.Controller;

import oasip.backend.DTOs.EventDTO.EventCreateDto;
import oasip.backend.DTOs.EventDTO.EventDetailDto;
import oasip.backend.DTOs.EventDTO.EventListAllDto;
import oasip.backend.Enitities.Event;
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
    public List<EventListAllDto> getAllEvent(){
        return service.getAllEvent();
    }

    @GetMapping("/{eventId}")
    public EventDetailDto getEvent(@PathVariable Integer eventId){
        return service.getEvent(eventId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public EventCreateDto create(@RequestBody EventCreateDto newEvent){
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable Integer eventId){
        service.deleteEvent(eventId);
    }

    @PutMapping("/{eventId}")
    public Event update(@PathVariable Integer eventId , @RequestBody EventListAllDto updateEvent){
        return service.updateEvent(updateEvent , eventId);
    }
}
