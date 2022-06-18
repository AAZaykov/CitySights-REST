package com.aazaykov.citysights.service;

public interface CityService {

    void addCity(String name, Long population, String metro, String country);

    boolean editCity(Long cityId, Long population, String metro);
}
