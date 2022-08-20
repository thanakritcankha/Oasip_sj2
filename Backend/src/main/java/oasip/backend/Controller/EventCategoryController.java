package oasip.backend.Controller;


<<<<<<< HEAD
import oasip.backend.DTOs.CategoryDTO.CategoryCreateDto;
import oasip.backend.DTOs.EventcategoryDto;
=======
import oasip.backend.DTOs.Category.CategoryCreateDto;
import oasip.backend.DTOs.Category.CategoryListCreateDto;
import oasip.backend.DTOs.Category.CategoryEditDto;
import oasip.backend.DTOs.Category.CategoryListAllDto;
import oasip.backend.DTOs.Category.CategoryListFilterDto;
>>>>>>> dev044
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
    public List<CategoryListAllDto> getAllCategory(){
        return service.getAllCategory();
    }

<<<<<<< HEAD
    @GetMapping("/{categoryId}")
    public CategoryCreateDto getCategory(@PathVariable Integer categoryId){
        return service.getCategory(categoryId);
=======
    @GetMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryListCreateDto> getAllCategoryForCreate(){
        return service.getAllCategoryForCreate();
    }

    @GetMapping("/period")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryListFilterDto> getAllCategoryForFilter(){
        return service.getAllCategoryForFilter();
>>>>>>> dev044
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryCreateDto getCategoryDetails(@PathVariable Integer categoryId){
        return service.getCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer categoryId){
        service.deleteCategory(categoryId);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Eventcategory update(@PathVariable Integer categoryId ,@Valid @RequestBody CategoryEditDto updateCategory){
        return service.updateCategory(updateCategory , categoryId);
    }

}
