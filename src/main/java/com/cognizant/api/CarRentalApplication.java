package com.cognizant.api;

import com.cognizant.api.config.CarRentalApplicationConfiguration;
import com.cognizant.api.exception.CityNotFoundException;
import com.cognizant.api.resource.CarRentalResource;
import com.cognizant.api.service.DistanceCalculator;
import com.cognizant.api.service.TravelExpenseService;
import com.cognizant.api.util.CarRentalUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class CarRentalApplication extends Application<CarRentalApplicationConfiguration> {

  @Override
  public void run(CarRentalApplicationConfiguration configuration, Environment environment) throws Exception {
    //TODO Application is starting up with sample distance data. In the future it should be replaced
    // by the real data pulled from database, file etc
    DistanceCalculator distanceCalculator = new DistanceCalculator(CarRentalUtil.getPopulatedMap());

    TravelExpenseService travelExpenseService = new TravelExpenseService(distanceCalculator);
    CarRentalResource carRentalResource = new CarRentalResource(travelExpenseService);
    environment.jersey().register(carRentalResource);
    environment.jersey().register(new CityNotFoundException.CityNotFoundExceptionMapper());
  }

  public static void main(String[] args) throws Exception {
    new CarRentalApplication().run(args);
  }
}
