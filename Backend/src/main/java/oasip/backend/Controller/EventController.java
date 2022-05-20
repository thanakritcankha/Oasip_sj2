package oasip.backend.Controller;

import oasip.backend.DTOs.Create.CreateEventDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.DTOs.Overlap.OverlapEventDto;
import oasip.backend.Service.EventService;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/category/{categoryId}")
    public List<ListAllEventDto> getOverlap(@PathVariable Integer categoryId){ return service.getOldEvent(categoryId);}

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEventDto create(@Valid @RequestBody CreateEventDto newEvent){
        System.out.println("dskalda;k");
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable Integer eventId){
        service.deleteEvent(eventId);
    }

    @PatchMapping("/{eventId}")
    public EditEventDto update(@PathVariable Integer eventId , @RequestBody EditEventDto updateEvent){
       return service.updateEvent(updateEvent , eventId);
    }
}
