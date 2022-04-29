package oasip.backend.Service;

import oasip.backend.DTOs.EventCategoryDto;
import oasip.backend.DTOs.EventDto;
import oasip.backend.Enitities.Event;
import oasip.backend.Enitities.EventCategory;
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

    public List<EventCategoryDto> getAllCategory() {
        List<EventCategory> categoryList = repository.findAll();
        return listMapper.maplist(categoryList, EventCategoryDto.class, modelMapper);
    }
    public EventCategoryDto getCategory(Integer categoryId){
        EventCategory category = repository.findById(categoryId).orElseThrow(
                () -> new RuntimeException(categoryId + " Does not Exist !!!" ));
        return modelMapper.map(category , EventCategoryDto.class);
    }

    public EventCategory createCategory(EventCategoryDto newCategory){
        EventCategory category = modelMapper.map(newCategory,EventCategory.class);
        //event category
        return repository.saveAndFlush(category);
    }
    public void deleteCategory(Integer categoryId){
        repository.findById(categoryId).orElseThrow(
                () -> new RuntimeException(categoryId + " Does not Exist !!!" ));
        repository.deleteById(categoryId);
    }
    public EventCategory updateCategory(EventCategoryDto updateCategory , Integer categoryId){
        EventCategory newCategory = modelMapper.map(updateCategory,EventCategory.class);
        EventCategory category = repository.findById(categoryId).map(o -> mapCategory(o,newCategory)).orElseGet(() -> {
            newCategory.setId(categoryId);
            return newCategory;
        });
        return repository.saveAndFlush(category);
    }
    private EventCategory mapCategory(EventCategory existingCategory, EventCategory updateCategory) {
        existingCategory.setEventCategoryName(updateCategory.getEventCategoryName());
        existingCategory.setEventCategoryDescription(updateCategory.getEventCategoryDescription());
        existingCategory.setEventDuration(updateCategory.getEventDuration());
        return existingCategory; }

}
