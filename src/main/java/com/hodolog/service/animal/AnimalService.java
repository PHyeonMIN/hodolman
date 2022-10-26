package com.hodolog.service.animal;

import com.hodolog.domain.AnimalType;
import org.springframework.stereotype.Component;

@Component
public interface AnimalService {

    String getSound();
    AnimalType getType();
}
