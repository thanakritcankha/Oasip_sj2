package oasip.backend.Controller;


import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.EventcategoryDto;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.Service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class EventCategoryController {
    @Autowired
    private EventCategoryService service;

    @GetMapping("")
    public List<EventcategoryDto> getAllCategory(){
        return service.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    public CreateEventcategoryDto getCategory(@PathVariable Integer categoryId){
        return service.getCategory(categoryId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public EventcategoryDto create(@RequestBody EventcategoryDto newCategory){
        return service.createCategory(newCategory);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Integer categoryId){
        service.deleteCategory(categoryId);
    }

    @PutMapping("/{categoryId}")
    public Eventcategory update(@PathVariable Integer categoryId , @RequestBody EventcategoryDto updateCategor){
        return service.updateCategory(updateCategor , categoryId);
    }

}
