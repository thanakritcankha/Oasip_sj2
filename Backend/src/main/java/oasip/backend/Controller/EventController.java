package oasip.backend.Controller;

import oasip.backend.DTOs.Create.ValidationCreateEventDto;
import oasip.backend.DTOs.Detail.DetailEventDto;
import oasip.backend.DTOs.Edits.EditEventDto;
import oasip.backend.DTOs.ListAll.ListAllEventDto;
import oasip.backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ListAllEventDto> getAllEvents() {
        return service.getAllEvent();
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailEventDto getEventDetail(@PathVariable Integer eventId) {
        return service.getEvent(eventId);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ListAllEventDto> filterEvents(@RequestParam(name = "categoryId") Integer id,
                                           @RequestParam(name = "option", defaultValue = "0" , required = false) Integer optionId ,
                                           @RequestParam(name = "time", defaultValue = "2022-10-02" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date time){
        return service.filterEvents(id , optionId , time);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ValidationCreateEventDto createEvent(@Valid @RequestBody ValidationCreateEventDto newEvent) {
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@PathVariable Integer eventId) {
        service.deleteEvent(eventId);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EditEventDto updateEvent(@PathVariable Integer eventId, @RequestBody EditEventDto updateEvent) {
        return service.updateEvent(updateEvent, eventId);
    }
}
