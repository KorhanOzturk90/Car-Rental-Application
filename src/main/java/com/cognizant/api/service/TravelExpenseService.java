package com.cognizant.api.service;

import com.cognizant.api.exception.CityNotFoundException;
import com.cognizant.api.model.FuelType;
import com.cognizant.api.model.VehicleType;

import java.util.List;

public class TravelExpenseService {

  private DistanceCalculator distanceCalculator;
  private static final double BUS_DISCOUNT_PERCENTAGE = 2;
  private static final double AC_ADDITIONAL_FEE = 2;
  private static final double EXTRA_PERSON_ADDITIONAL_FEE = 1;

  public TravelExpenseService(DistanceCalculator distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  public double calculateTotalCarHireExpense(String carType, String fuelType, boolean hasAC,
                                             List<String> citiesVisited, int noPassengers) throws CityNotFoundException

  {
    double totalCost, totalRatePerKM;
    double totalDistanceCovered = distanceCalculator.getTotalDistance(citiesVisited);
    VehicleType vehicleType = VehicleType.valueOf(carType);
    FuelType fuel = FuelType.valueOf(fuelType);
    totalRatePerKM = fuel.getRate();
    if (hasAC) {
      totalRatePerKM += AC_ADDITIONAL_FEE;
    }
    if (noPassengers > vehicleType.getMaxPerson()) {
      totalRatePerKM += EXTRA_PERSON_ADDITIONAL_FEE * (noPassengers - vehicleType.getMaxPerson());
    }
    totalCost = totalDistanceCovered * totalRatePerKM;
    if (vehicleType == VehicleType.BUS) {
      totalCost -= totalCost / 100 * BUS_DISCOUNT_PERCENTAGE;
    }

    return totalCost;
  }
}
