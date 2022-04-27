package oasip.backend.Controller;

import oasip.backend.DTOs.EventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping("")
    public List<EventDto> getAllEvent(){
        return service.getAllEvent();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@ModelAttribute EventDto newEvent){
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void delect(@PathVariable Integer eventId){

    }
}
