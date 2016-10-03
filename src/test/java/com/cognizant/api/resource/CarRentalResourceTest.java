package com.cognizant.api.resource;

import static org.junit.Assert.assertEquals;

import com.cognizant.api.exception.CityNotFoundException;
import com.cognizant.api.service.DistanceCalculator;
import com.cognizant.api.service.TravelExpenseService;
import com.cognizant.api.util.CarRentalUtil;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class CarRentalResourceTest {

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
      .addResource(new CarRentalResource(new TravelExpenseService
          (new DistanceCalculator(CarRentalUtil.getPopulatedMap()))))
      .addResource(new CityNotFoundException.CityNotFoundExceptionMapper())
      .build();

  @Test
  public void shouldCalculateTheCostForTwoCities() {
    Response response = resources.client()
        .target("/rent-a-car?carType=CAR&fuelType=DIESEL&hasAC=false&cities=Pune&cities=Mumbai&noPassengers=4")
        .request().get();
    assertEquals(200, response.getStatus());
    assertEquals(2800d, response.readEntity(Double.class), 0.0);
  }

  @Test
  public void shouldCalculateTheCostForMultipleCities() {
    Response response = resources.client()
        .target("/rent-a-car?carType=CAR&fuelType=DIESEL&hasAC=false&cities=Pune&cities=Mumbai&cities=Delhi&noPassengers=4")
        .request().get();
    assertEquals(200, response.getStatus());
    assertEquals(31500d, response.readEntity(Double.class), 0.0);
  }

  @Test
  public void shouldDealWithUnknownCity() {
    Response response = resources.client()
        .target("/rent-a-car?carType=CAR&fuelType=DIESEL&hasAC=false&cities=Cambridge&cities=Mumbai&cities=Delhi&noPassengers=4")
        .request().get();
    assertEquals(404, response.getStatus());
  }
}
