package com.hodolog.service.animal;

import com.hodolog.domain.AnimalType;

//@Component
public class CatService implements AnimalService {

    @Override
    public String getSound() {
        return "냐옹";
    }

    @Override
    public AnimalType getType() {
        return AnimalType.CAT;
    }
}
