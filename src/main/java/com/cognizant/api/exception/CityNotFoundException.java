package com.cognizant.api.exception;

import io.dropwizard.jersey.errors.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CityNotFoundException extends RuntimeException {

  public CityNotFoundException(String city) {
    super("City Not Found in database: " + city);
  }

  public static class CityNotFoundExceptionMapper implements ExceptionMapper<CityNotFoundException> {

    @Override
    public Response toResponse(CityNotFoundException exception) {
      return Response.status(404).entity(new ErrorMessage(404, exception.getMessage())).build();
    }

  }
}
