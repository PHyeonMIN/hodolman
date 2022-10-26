package com.hodolog.controller;

// type -> cat -> CarService
// type -> dog -> DogService

// cat -> 냐옹
// dog -> 멍멍

import com.hodolog.service.animal.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 1. component list 주입
// 2. map (key:beanName , value:service)
// 3. enum

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnimalController {

//    private final AnimalServiceFinder animalServiceFinder;

    private final Map<String, AnimalService> animalServices;

    @GetMapping("/sound")
    public String sound(@RequestParam String type){
        log.info("animalService={}, keys={}", animalServices, animalServices.keySet());

        // "CAT" -> "catService"
        AnimalService service = animalServices.get(type.toLowerCase() + "Service");
        return service.getSound();
//        AnimalService service = animalServiceFinder.find(type);
//        return service.getSound();

//        if(type.equals("CAT")){
//            return new CatService().getSound();
//        }else if(type.equals("DOG")) {
//            return new DogService().getSound();
//        }else {
//            return "모르는 동물";
//        }
    }
}
