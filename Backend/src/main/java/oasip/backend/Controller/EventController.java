package oasip.backend.Controller;

import oasip.backend.DTOs.Create.CreateEventDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.EventDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.DTOs.OverlapEventDto;
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
    public List<ListAllEventDto> getAllEvent(){
        return service.getAllEvent();
    }

    @GetMapping("/{eventId}")
    public DetailEventDto getEvent(@PathVariable Integer eventId){
        return service.getEvent(eventId);
    }

    @GetMapping("/allstarttime")
    public List<OverlapEventDto> getOverlap(){ return }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEventDto create(@RequestBody CreateEventDto newEvent){
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable Integer eventId){
        service.deleteEvent(eventId);
    }

    @PutMapping("/{eventId}")
    public EventDto update(@PathVariable Integer eventId , @RequestBody EditEventDto updateEvent){
       return service.updateEvent(updateEvent , eventId);
    }
}
