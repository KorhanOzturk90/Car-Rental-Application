package com.cognizant.api.service;

import com.cognizant.api.exception.CityNotFoundException;
import com.cognizant.api.model.MergedCitiesPair;

import java.util.List;
import java.util.Map;

public class DistanceCalculator {

  private Map<MergedCitiesPair, Double> distanceLookUpMap = new java.util.HashMap<>();

  public DistanceCalculator(Map<MergedCitiesPair, Double> distanceLookUpMap) {
    this.distanceLookUpMap = distanceLookUpMap;
  }

  double getDistance(String startingCity, String destinationCity) {
    if (startingCity.equalsIgnoreCase(destinationCity)) {
      return 0;
    }
    MergedCitiesPair mergedCitiesPairKey = new MergedCitiesPair(startingCity, destinationCity);
    if (distanceLookUpMap.containsKey(mergedCitiesPairKey)) {
      return distanceLookUpMap.get(mergedCitiesPairKey);
    } else{
      throw new CityNotFoundException(String.format("%s-%s", startingCity, destinationCity));
    }
  }

  double getTotalDistance(List<String> citiesVisited) throws CityNotFoundException{
    double totalDistanceCovered = 0.0;
    for (int i = 0; i < citiesVisited.size() - 1; i++) {
      totalDistanceCovered += getDistance(citiesVisited.get(i), citiesVisited.get(i + 1));
    }
    return totalDistanceCovered;
  }
}
