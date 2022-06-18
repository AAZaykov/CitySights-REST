package com.aazaykov.citysights.service;

import com.aazaykov.citysights.entity.Sight;

import java.util.Date;
import java.util.List;

public interface SightService {

    List<Sight> allSights(boolean sortByName, String typeFilter);

    List<Sight> sightsOfCity(Long cityId);

    void addSight(String name, Date date, String description, String type, Long city);

    void editSight(Long id, String description);

    boolean deleteSight(Long sightId);

}
