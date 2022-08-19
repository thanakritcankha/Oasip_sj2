package oasip.backend.Controller;


import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Create.ListEventcategoryDto;
import oasip.backend.DTOs.Edits.EditEventcategoryDto;
import oasip.backend.DTOs.ListAll.ListAllEventcategoryDto;
import oasip.backend.DTOs.ListAll.ListEventcategoryNameDto;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.Service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class EventCategoryController {
    @Autowired
    private EventCategoryService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ListAllEventcategoryDto> getAllCategory(){
        return service.getAllCategory();
    }

    @GetMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public List<ListEventcategoryDto> getAllCategoryForCreate(){
        return service.getAllCategoryForCreate();
    }

    @GetMapping("/period")
    @ResponseStatus(HttpStatus.OK)
    public List<ListEventcategoryNameDto> getAllCategoryForFilter(){
        return service.getAllCategoryForFilter();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CreateEventcategoryDto getCategoryDetails(@PathVariable Integer categoryId){
        return service.getCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer categoryId){
        service.deleteCategory(categoryId);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Eventcategory update(@PathVariable Integer categoryId ,@Valid @RequestBody EditEventcategoryDto updateCategory){
        return service.updateCategory(updateCategory , categoryId);
    }

}
