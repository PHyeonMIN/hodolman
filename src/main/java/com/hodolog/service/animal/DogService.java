package com.hodolog.service.animal;

import com.hodolog.domain.AnimalType;

public class DogService implements AnimalService {

    @Override
    public String getSound() {
        return "멍멍";
    }

    @Override
    public AnimalType getType() {
        return AnimalType.DOG;
    }
}
