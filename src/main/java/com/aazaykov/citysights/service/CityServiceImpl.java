package com.aazaykov.citysights.service;

import com.aazaykov.citysights.entity.City;
import com.aazaykov.citysights.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void addCity(String name, Long population, String metro, String country) {
        boolean isMetro = Boolean.parseBoolean(metro);
        City city = new City(name, population, isMetro, country);
        cityRepository.save(city);
    }

    @Override
    public boolean editCity(Long cityId, Long updatePopulation, String updateMetro) {
        if (cityRepository.existsById(cityId)){
            City city = cityRepository.findById(cityId).get();
            if (updatePopulation != null){
                city.setPopulation(updatePopulation);
            }
            if (updateMetro != null){
                Boolean isMetro = Boolean.valueOf(updateMetro);
                city.setMetroAvailable(isMetro);
            }
            cityRepository.save(city);
            return true;
        }
        return false;
    }
}