package com.cognizant.api.service;

import static org.junit.Assert.assertEquals;

import com.cognizant.api.util.CarRentalUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TravelExpenseServiceTest {
  private TravelExpenseService travelExpenseService;

  @Before
  public void setUp() {
    DistanceCalculator distanceCalculator = new DistanceCalculator(CarRentalUtil.getPopulatedMap());
    travelExpenseService = new TravelExpenseService(distanceCalculator);
  }

  @Test
  public void calculatesTotalExpenseSuccessfully(){
   assertEquals(19200.0,travelExpenseService.
       calculateTotalCarHireExpense("SUV", "DIESEL", true, Arrays.asList("Mumbai", "Pune", "Bangalore"), 3), 0);
  }

  @Test
  public void calculatesTotalExpenseSuccessfullyWithNoAC(){
    assertEquals(16800.0,travelExpenseService.
        calculateTotalCarHireExpense("SUV", "DIESEL", false, Arrays.asList("Mumbai", "Pune", "Bangalore"), 5), 0);
  }

  @Test
  public void calculatesTotalExpenseForExtra2PersonWithNoAC(){
    assertEquals(19200.0,travelExpenseService.
        calculateTotalCarHireExpense("SUV", "DIESEL", false, Arrays.asList("Mumbai", "Pune", "Bangalore"), 8), 0);
  }

  @Test
  public void calculatesTotalExpenseForExtra2PersonWithAC(){
    assertEquals(21600.0,travelExpenseService.
        calculateTotalCarHireExpense("SUV", "DIESEL", true, Arrays.asList("Mumbai", "Pune", "Bangalore"), 8), 0);
  }

  @Test
  public void calculatesTotalExpenseSuccessfullyWithNoACandBusDiscount(){
    assertEquals(16464.0,travelExpenseService.
        calculateTotalCarHireExpense("BUS", "DIESEL", false, Arrays.asList("Mumbai", "Pune", "Bangalore"), 25), 0);
  }

  @Test
  public void calculatesTotalExpenseSuccessfullyWithNoACandPetrolBusDiscount(){
    assertEquals(17640.0,travelExpenseService.
        calculateTotalCarHireExpense("BUS", "PETROL", false, Arrays.asList("Mumbai", "Pune", "Bangalore"), 25), 0);
  }

  @Test
  public void calculatesTotalExpenseSuccessfullyWithACandPetrolBusDiscount(){
    assertEquals(19992.0,travelExpenseService.
        calculateTotalCarHireExpense("BUS", "PETROL", true, Arrays.asList("Mumbai", "Pune", "Bangalore"), 25), 0);
  }
}
