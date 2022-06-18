package com.aazaykov.citysights.controller;

import com.aazaykov.citysights.entity.Sight;
import com.aazaykov.citysights.entity.SightType;
import com.aazaykov.citysights.service.SightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sights")
public class SightController {

    private final SightService sightService;

    public SightController(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping
    public ResponseEntity<List<Sight>> getAllSights(@RequestParam(value = "sortByName", required = false) boolean sortByName,
                                              @RequestParam(value = "filterByType", required = false) String type){
        List<Sight> sights = sightService.allSights(sortByName, type);
        if (type != null){
            sights.removeIf(s -> s.getType() != SightType.valueOf(type));
        }
        return sights != null && !sights.isEmpty()
                ? new ResponseEntity<>(sights, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<List<Sight>> sightsOfCity(@PathVariable("cityId") Long cityId){
        List<Sight> sights = sightService.sightsOfCity(cityId);
        return sights != null && !sights.isEmpty()
                ? new ResponseEntity<>(sights, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSight(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "date") String date,
                                      @RequestParam(value = "description") String description,
                                      @RequestParam(value = "typeSight") String type,
                                      @RequestParam(value = "cityId") Long cityId
                                      ) throws ParseException {
        Date newDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        sightService.addSight(name, newDate, description, type, cityId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{sightId}")
    public ResponseEntity<?> editSight(@PathVariable("sightId") Long sightId,
                                       @RequestParam(value = "description", required = false) String newDescription){
        sightService.editSight(sightId, newDescription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{sightId}")
    public ResponseEntity<?> deleteSight(@PathVariable("sightId") Long sightId){
        return sightService.deleteSight(sightId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}