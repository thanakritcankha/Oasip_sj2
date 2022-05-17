package oasip.backend.Controller;

import oasip.backend.DTOs.Create.CreateDto;
import oasip.backend.DTOs.Create.CreateEventDto;
import oasip.backend.DTOs.Detail.DetailDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.Edits.ResPatchDto;
import oasip.backend.DTOs.EventDto;
import oasip.backend.DTOs.ListAll.ListAllDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.DTOs.Overlap.OverlapEventDto;
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
    public List<ListAllDto> getAllEvent(){
        return service.getAllEvent();
    }

    @GetMapping("/{eventId}")
    public DetailDto getEvent(@PathVariable Integer eventId){
        return service.getEvent(eventId);
    }

    @GetMapping("/overlap/{categoryId}")
    public List<OverlapEventDto> getOverlap(@PathVariable Integer categoryId){ return service.getOldEvent(categoryId);}

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDto create(@RequestBody CreateDto newEvent){
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable Integer eventId){
        service.deleteEvent(eventId);
    }

    @PatchMapping("/{eventId}")
    public ResPatchDto update(@PathVariable Integer eventId , @RequestBody EditEventDto updateEvent){
       return service.updateEvent(updateEvent , eventId);
    }
}
