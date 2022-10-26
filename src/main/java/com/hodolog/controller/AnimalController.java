package com.hodolog.controller;

// type -> cat -> CarService
// type -> dog -> DogService

// cat -> 냐옹
// dog -> 멍멍

import com.hodolog.service.animal.AnimalService;
import com.hodolog.service.animal.AnimalServiceFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalServiceFinder animalServiceFinder;

    @GetMapping("/sound")
    public String sound(@RequestParam String type){
        AnimalService service = animalServiceFinder.find(type);
        return service.getSound();

//        if(type.equals("CAT")){
//            return new CatService().getSound();
//        }else if(type.equals("DOG")) {
//            return new DogService().getSound();
//        }else {
//            return "모르는 동물";
//        }
    }
}
