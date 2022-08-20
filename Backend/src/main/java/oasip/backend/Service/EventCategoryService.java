package oasip.backend.Service;

import oasip.backend.DTOs.Category.CategoryCreateDto;
import oasip.backend.DTOs.Category.CategoryListCreateDto;
import oasip.backend.DTOs.Category.CategoryEditDto;
import oasip.backend.DTOs.Category.CategoryListAllDto;
import oasip.backend.DTOs.Category.CategoryListFilterDto;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.ListMapper;
import oasip.backend.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class EventCategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<CategoryListAllDto> getAllCategory() {
        List<Eventcategory> categoryList = repository.findAll();
        Collections.reverse(categoryList);
        return listMapper.maplist(categoryList, CategoryListAllDto.class, modelMapper);
    }

    public List<CategoryListCreateDto> getAllCategoryForCreate() {
        List<Eventcategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, CategoryListCreateDto.class, modelMapper);
    }
    public List<CategoryListFilterDto> getAllCategoryForFilter() {
        List<Eventcategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, CategoryListFilterDto.class, modelMapper);
    }

    public CategoryCreateDto getCategory(Integer categoryId){
        Eventcategory category = repository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND,categoryId + " Does not Exist !!!" ));
        return modelMapper.map(category , CategoryCreateDto.class);
    }

    public void deleteCategory(Integer categoryId){
        repository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,categoryId + " Does not Exist !!!" ));
        repository.deleteById(categoryId);
    }
    public Eventcategory updateCategory(CategoryEditDto updateCategory , Integer categoryId){
        Eventcategory newCategory = modelMapper.map(updateCategory,Eventcategory.class);
        Eventcategory category = repository.findById(categoryId).map(o -> mapCategory(o,newCategory)).orElseGet(() -> {
            newCategory.setId(categoryId);
            return newCategory;
        });
        return repository.saveAndFlush(category);
    }
    private Eventcategory mapCategory(Eventcategory existingCategory, Eventcategory updateCategory) {
        if (updateCategory.getEventCategoryName() != null){
            existingCategory.setEventCategoryName(updateCategory.getEventCategoryName().trim());
        }
        if (updateCategory.getEventCategoryDescription() != null){
            existingCategory.setEventCategoryDescription(updateCategory.getEventCategoryDescription().trim());
        }
        if(updateCategory.getEventCategoryDuration() != null){
            existingCategory.setEventCategoryDuration(updateCategory.getEventCategoryDuration());
        }

        return existingCategory; }

}
