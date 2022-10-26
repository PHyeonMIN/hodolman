package com.hodolog.domain;

import com.hodolog.service.animal.AnimalService;
import com.hodolog.service.animal.CatService;
import com.hodolog.service.animal.DogService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
public enum AnimalType {
    CAT(CatService.class),
    DOG(DogService.class);

    private final Class<? extends AnimalService> animalService;

//    AnimalType(Class<? extends AnimalService> animalService) {
//        this.animalService = animalService;
//    }

    public AnimalService create() {
        try {
            return animalService.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
