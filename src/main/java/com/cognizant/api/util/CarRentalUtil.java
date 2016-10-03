package com.cognizant.api.util;

import com.cognizant.api.model.MergedCitiesPair;

import java.util.HashMap;
import java.util.Map;

public class CarRentalUtil {

  public static Map<MergedCitiesPair, Double> getPopulatedMap() {
    MergedCitiesPair<String, String> puneToMumbai = new MergedCitiesPair<>("Pune", "Mumbai");
    MergedCitiesPair<String, String> puneToBangalore = new MergedCitiesPair<>("Pune", "Bangalore");
    MergedCitiesPair<String, String> mumbaiToDelhi = new MergedCitiesPair<>("Mumbai", "Delhi");
    MergedCitiesPair<String, String> mumbaiToChennai = new MergedCitiesPair<>("Mumbai", "Chennai");

    Map<MergedCitiesPair, Double> sampleDistancesMap = new HashMap<>();
    sampleDistancesMap.put(puneToMumbai, 200d);
    sampleDistancesMap.put(puneToBangalore, 1000d);
    sampleDistancesMap.put(mumbaiToDelhi, 2050d);
    sampleDistancesMap.put(mumbaiToChennai, 1234.5);
    return sampleDistancesMap;
  }
}
