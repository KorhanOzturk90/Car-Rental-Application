package com.cognizant.api.model;

public enum FuelType {

  PETROL(15),
  DIESEL(14);

  private double rate;

  FuelType(double rate) {
    this.rate = rate;
  }

  public double getRate() {
    return rate;
  }
}
