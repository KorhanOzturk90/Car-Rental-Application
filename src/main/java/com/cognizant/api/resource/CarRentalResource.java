package com.cognizant.api.resource;

import com.cognizant.api.service.TravelExpenseService;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rent-a-car")
@Produces({MediaType.APPLICATION_JSON})
public class CarRentalResource {

  private TravelExpenseService expenseService;

  public CarRentalResource(TravelExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @GET
  public double getEstimatedRentalExpense(
      @NotNull @QueryParam("carType") final String carType,
      @NotNull @QueryParam("fuelType") final String fuelType,
      @NotNull @QueryParam("hasAC") final boolean hasAC,
      @NotNull @QueryParam("cities") final List<String> citiesVisited,
      @NotNull @QueryParam("noPassengers") final int noPassengers) {

    return expenseService.calculateTotalCarHireExpense(carType, fuelType, hasAC, citiesVisited, noPassengers);
  }
}
