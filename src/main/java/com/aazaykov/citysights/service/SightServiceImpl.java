package com.aazaykov.citysights.service;

import com.aazaykov.citysights.entity.City;
import com.aazaykov.citysights.entity.Sight;
import com.aazaykov.citysights.entity.SightType;
import com.aazaykov.citysights.repository.CityRepository;
import com.aazaykov.citysights.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SightServiceImpl implements SightService {

    @Autowired
    private SightRepository sightRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<Sight> allSights(boolean sortByName, String typeFilter) {
        List<Sight> allSights = sightRepository.findAll();
        if (sortByName == true){
            allSights = sightRepository.findAll(Sort.by("name").ascending());
        }
        if (typeFilter != null){
            SightType sightType = SightType.valueOf(typeFilter);
            allSights.stream().filter(s -> s.getType().equals(sightType)).collect(Collectors.toList());
        }
        return allSights;
    }

    @Override
    public List<Sight> sightsOfCity(Long cityId) {
        List<Sight> citySights = sightRepository.findAll();
        City city = cityRepository.findById(cityId).get();
        List<Sight> result = citySights.stream().filter(s -> s.getCityId() == city.getId()).collect(Collectors.toList());
        return result;
    }

    @Override
    public void addSight(String name, Date date, String description, String type, Long cityId) {
        SightType sightType = SightType.valueOf(type);
        Sight sight = new Sight(name, date, description, sightType, cityId);
        sightRepository.save(sight);
    }

    @Override
    public void editSight(Long id, String description) {
        Sight sight = sightRepository.findById(id).get();
        sight.setDescription(description);
        sightRepository.save(sight);
    }

    @Override
    public boolean deleteSight(Long sightId) {
        if (sightRepository.existsById(sightId)){
            sightRepository.deleteById(sightId);
            return true;
        }
        return false;
    }
}