package oasip.backend.Controller;

<<<<<<< HEAD
import oasip.backend.DTOs.EventDTO.EventCreateDto;
import oasip.backend.DTOs.EventDTO.EventDetailDto;
import oasip.backend.DTOs.EventDTO.EventListAllDto;
import oasip.backend.Enitities.Event;
=======
import oasip.backend.DTOs.Event.EventCreateDto;
import oasip.backend.DTOs.Event.EventDetailDto;
import oasip.backend.DTOs.Event.EventEditDto;
import oasip.backend.DTOs.Event.EventListAllDto;
>>>>>>> dev044
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
<<<<<<< HEAD
    public List<EventListAllDto> getAllEvent(){
        return service.getAllEvent();
    }

    @GetMapping("/{eventId}")
    public EventDetailDto getEvent(@PathVariable Integer eventId){
=======
    public List<EventListAllDto> getAllEvents() { return service.getAllEvent(); }

    @GetMapping("/{eventId}")
    public EventDetailDto getEventDetail(@PathVariable Integer eventId) {
>>>>>>> dev044
        return service.getEvent(eventId);
    }

    @GetMapping("/")
    public List<EventListAllDto> filterEvents(@RequestParam(name = "categoryId") Integer id,
                                              @RequestParam(name = "option", defaultValue = "0" , required = false) Integer optionId ,
                                              @RequestParam(name = "time", defaultValue = "2022-10-02" , required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date time){
        return service.filterEvents(id , optionId , time);
    }

    @PostMapping("")
<<<<<<< HEAD
    @ResponseStatus(HttpStatus.CREATED)
    public EventCreateDto create(@RequestBody EventCreateDto newEvent){
=======
    @ResponseStatus(HttpStatus.OK)
    public EventCreateDto createEvent(@Valid @RequestBody EventCreateDto newEvent) {
>>>>>>> dev044
        return service.createEvent(newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Integer eventId) {
        service.deleteEvent(eventId);
    }

<<<<<<< HEAD
    @PutMapping("/{eventId}")
    public Event update(@PathVariable Integer eventId , @RequestBody EventListAllDto updateEvent){
        return service.updateEvent(updateEvent , eventId);
=======
    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventEditDto updateEvent(@PathVariable Integer eventId, @RequestBody EventEditDto updateEvent) {
        return service.updateEvent(updateEvent, eventId);
>>>>>>> dev044
    }
}
