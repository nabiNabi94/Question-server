package ru.digitalliague.questionsserver.util;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private  ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public   <S, T> List<T> mapping(List<S> source, Class<T> targetClass) {
//        return modelMapper.map(source,targetClass);
        modelMapper.getConfiguration().setPropertyCondition(context -> {
            //if the object is a PersistentCollection could be not initialized
            //in case of lazy strategy, in this case the object will not be mapped:
            return (!(context.getSource() instanceof PersistentCollection)
                    || ((PersistentCollection)context.getSource()).wasInitialized());
        });
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


    public   <S, T> T mapping(S source, Class<T> targetClass) {
        modelMapper.getConfiguration().setPropertyCondition(context -> {
            //if the object is a PersistentCollection could be not initialized
            //in case of lazy strategy, in this case the object will not be mapped:
            return (!(context.getSource() instanceof PersistentCollection)
                    || ((PersistentCollection)context.getSource()).wasInitialized());
        });
        return modelMapper.map(source,targetClass);
    }

    @Transactional
    public  <S, T> T skipNullFieldsMapping(S source,Class<T> tClass){
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(source,tClass);

    }
}
