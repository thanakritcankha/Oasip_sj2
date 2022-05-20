package oasip.backend.Service;

import oasip.backend.DTOs.Create.CreateEventcategoryDto;
import oasip.backend.DTOs.Create.ListEventcategoryDto;
import oasip.backend.DTOs.EventcategoryDto;
import oasip.backend.DTOs.ListAll.ListAllEventcategoryDto;
import oasip.backend.DTOs.ListAll.ListEventcategoryForFilterDto;
import oasip.backend.Enitities.Eventcategory;
import oasip.backend.ListMapper;
import oasip.backend.repositories.EventcategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ListEventcategoryForFilterDto> getAllCategoryForFilter() {
        List<Eventcategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, ListEventcategoryForFilterDto.class, modelMapper);
    }

    public CreateEventcategoryDto getCategory(Integer categoryId){
        Eventcategory category = repository.findById(categoryId).orElseThrow(
                () -> new RuntimeException(categoryId + " Does not Exist !!!" ));
        return modelMapper.map(category , CreateEventcategoryDto.class);
    }

    public EventcategoryDto createCategory(EventcategoryDto newCategory){
        Eventcategory category = modelMapper.map(newCategory,Eventcategory.class);
        //event category
        System.out.println(category);
        repository.saveAndFlush(category);
        return newCategory;
    }
    public void deleteCategory(Integer categoryId){
        repository.findById(categoryId).orElseThrow(
                () -> new RuntimeException(categoryId + " Does not Exist !!!" ));
        repository.deleteById(categoryId);
    }
    public Eventcategory updateCategory(EventcategoryDto updateCategory , Integer categoryId){
        Eventcategory newCategory = modelMapper.map(updateCategory,Eventcategory.class);
        Eventcategory category = repository.findById(categoryId).map(o -> mapCategory(o,newCategory)).orElseGet(() -> {
            newCategory.setId(categoryId);
            return newCategory;
        });
        return repository.saveAndFlush(category);
    }
    private Eventcategory mapCategory(Eventcategory existingCategory, Eventcategory updateCategory) {
        existingCategory.setEventCategoryName(updateCategory.getEventCategoryName());
        existingCategory.setEventCategoryDescription(updateCategory.getEventCategoryDescription());
        existingCategory.setEventCategoryDuration(updateCategory.getEventCategoryDuration());
        return existingCategory; }

}
