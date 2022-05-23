package oasip.backend.Controller;


import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Create.ListEventcategoryDto;
import oasip.backend.DTOs.Edits.EditEventcategoryDto;
import oasip.backend.DTOs.EventcategoryDto;
import oasip.backend.DTOs.ListAll.ListAllEventcategoryDto;
import oasip.backend.DTOs.ListAll.ListEventcategoryForFilterDto;
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
    public List<ListAllEventcategoryDto> getAllCategory(){
        return service.getAllCategory();
    }
    @GetMapping("/create")
    public List<ListEventcategoryDto> getAllCategoryForCreate(){
        return service.getAllCategoryForCreate();
    }
    @GetMapping("/filter")
    public List<ListEventcategoryForFilterDto> getAllCategoryForFilter(){
        return service.getAllCategoryForFilter();
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

    @PatchMapping("/{categoryId}")
    public Eventcategory update(@PathVariable Integer categoryId , @RequestBody EditEventcategoryDto updateCategory){
        return service.updateCategory(updateCategory , categoryId);
    }

}
