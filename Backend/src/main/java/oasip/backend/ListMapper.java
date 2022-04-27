package oasip.backend;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private ListMapper() {}
    public <S,T>List<T> maplist(List<S> source, Class<T> targetClass , ModelMapper modelMaper){
        return source.stream().map(entity -> modelMaper.map(entity,targetClass))
                .collect(Collectors.toList());
    }
    public static ListMapper getInstance(){
        return listMapper;
    }
//    public <S,T> PageDTO<T> toPageDTO()
}
