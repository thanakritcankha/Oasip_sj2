package oasip.backend.Service;

import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Create.ListEventcategoryDto;
import oasip.backend.DTOs.Edits.EditEventcategoryDto;
import oasip.backend.DTOs.ListAll.ListAllEventcategoryDto;
import oasip.backend.DTOs.ListAll.ListEventcategoryNameDto;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.ListMapper;
import oasip.backend.Validation.Validations;
import oasip.backend.repositories.EventcategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventCategoryService {
    @Autowired
    private EventcategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<ListAllEventcategoryDto> getAllCategory() {
        List<Eventcategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, ListAllEventcategoryDto.class, modelMapper);
    }

    public List<ListEventcategoryDto> getAllCategoryForCreate() {
        List<Eventcategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, ListEventcategoryDto.class, modelMapper);
    }
    public List<ListEventcategoryNameDto> getAllCategoryForFilter() {
        List<Eventcategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, ListEventcategoryNameDto.class, modelMapper);
    }

    public CreateEventcategoryDto getCategory(Integer categoryId){
        Eventcategory category = repository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND,categoryId + " Does not Exist !!!" ));
        return modelMapper.map(category , CreateEventcategoryDto.class);
    }

    public void deleteCategory(Integer categoryId){
        repository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,categoryId + " Does not Exist !!!" ));
        repository.deleteById(categoryId);
    }
    public Eventcategory updateCategory(EditEventcategoryDto updateCategory , Integer categoryId){
        List<Eventcategory> categoryList = repository.findAll();
        Validations validations = new Validations();
        validations.uniqueName(updateCategory.getEventCategoryName() , categoryList);
        validations.duration(updateCategory.getEventCategoryDuration());
        if (validations.getTextError().length() > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,validations.getTextError());
        }
        Eventcategory newCategory = modelMapper.map(updateCategory,Eventcategory.class);
        Eventcategory category = repository.findById(categoryId).map(o -> mapCategory(o,newCategory)).orElseGet(() -> {
            newCategory.setId(categoryId);
            return newCategory;
        });
        return repository.saveAndFlush(category);
    }
    private Eventcategory mapCategory(Eventcategory existingCategory, Eventcategory updateCategory) {
        if (updateCategory.getEventCategoryName() != null){
            existingCategory.setEventCategoryName(updateCategory.getEventCategoryName());
        }
        if (updateCategory.getEventCategoryDescription() != null){
            existingCategory.setEventCategoryDescription(updateCategory.getEventCategoryDescription());
        }
        if(updateCategory.getEventCategoryDuration() != null){
            existingCategory.setEventCategoryDuration(updateCategory.getEventCategoryDuration());
        }

        return existingCategory; }

}
