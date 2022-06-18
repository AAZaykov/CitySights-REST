package com.aazaykov.citysights.controller;

import com.aazaykov.citysights.entity.City;
import com.aazaykov.citysights.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "population") Long population,
                                        @RequestParam(value = "metro") String metro,
                                        @RequestParam(value = "country") String country
                                        ){
        cityService.addCity(name, population, metro, country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{cityId}")
    public ResponseEntity<City> editCity(@PathVariable("cityId") Long cityId,
                                         @RequestParam(value = "population", required = false) Long newPopulation,
                                         @RequestParam(value = "metro", required = false) String newMetro
                                         ){
        if (cityService.editCity(cityId, newPopulation, newMetro)){
            return new ResponseEntity<>(HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
