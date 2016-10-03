package com.cognizant.api.service;

import static org.junit.Assert.assertEquals;

import com.cognizant.api.exception.CityNotFoundException;
import com.cognizant.api.model.MergedCitiesPair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistanceCalculatorTest {
  private Map<MergedCitiesPair, Double> sampleDistancesMap = new HashMap<>();

  @Before
  public void setUp() {
    MergedCitiesPair<String, String> londonToBristol = new MergedCitiesPair<>("London", "Bristol");
    MergedCitiesPair<String, String> londonToBrighton = new MergedCitiesPair<>("London", "Brighton");
    MergedCitiesPair<String, String> bristolToBrighton = new MergedCitiesPair<>("Bristol", "Brighton");
    MergedCitiesPair<String, String> bristolToDorset = new MergedCitiesPair<>("Bristol", "Dorset");

    sampleDistancesMap.put(londonToBristol, 190d);
    sampleDistancesMap.put(londonToBrighton, 120d);
    sampleDistancesMap.put(bristolToBrighton, 195d);
    sampleDistancesMap.put(bristolToDorset, 90d);
  }

  @Test
  public void shouldReturn0ForSameLocation(){
    DistanceCalculator distanceCalculator = new DistanceCalculator(sampleDistancesMap);
    assertEquals(0, distanceCalculator.getDistance("London", "London"), 0);
  }

  @Test(expected = CityNotFoundException.class)
  public void shouldThrowExceptionForUnknownCity(){
    DistanceCalculator distanceCalculator = new DistanceCalculator(sampleDistancesMap);
    assertEquals(0, distanceCalculator.getDistance("London", "Paris"), 0);
  }

  @Test
  public void shouldCalculateSingleJourneyDistance() {
    DistanceCalculator distanceCalculator = new DistanceCalculator(sampleDistancesMap);
    assertEquals(190d, distanceCalculator.getDistance("Bristol", "London"), 0);
    assertEquals(90d, distanceCalculator.getDistance("Bristol", "Dorset"), 0);
  }

  @Test
  public void shouldCalculateRoundTripDistance() {
    DistanceCalculator distanceCalculator = new DistanceCalculator(sampleDistancesMap);
    assertEquals(505d, distanceCalculator.getTotalDistance(Arrays.asList("London", "Brighton", "Bristol", "London")), 0);
  }
}
