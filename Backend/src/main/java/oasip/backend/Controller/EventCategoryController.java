package oasip.backend.Controller;


import oasip.backend.DTOs.EventCategoryDto;
import oasip.backend.DTOs.EventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.Enitities.EventCategory;
import oasip.backend.Service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@RestController
@RequestMapping("/api/categorys")
public class EventCategoryController {
    @Autowired
    private EventCategoryService service;

    @GetMapping("")
    public List<EventCategoryDto> getAllCategory(){
        return service.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    public EventCategoryDto getCategory(@PathVariable Integer categoryId){
        return service.getCategory(categoryId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public EventCategory create(@RequestBody EventCategoryDto newCategory){
        return service.createCategory(newCategory);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Integer categoryId){
        service.deleteCategory(categoryId);
    }

    @PutMapping("/{categoryId}")
    public EventCategory update(@PathVariable Integer categoryId , @RequestBody EventCategoryDto updateCategor){
        return service.updateCategory(updateCategor , categoryId);
    }

}
