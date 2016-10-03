package com.cognizant.api.model;

public enum VehicleType {

  SUV(6),
  CAR(5),
  VAN(10),
  BUS(30);

  private int maxPerson;

  VehicleType(int maxPerson) {
    this.maxPerson = maxPerson;
  }

  public int getMaxPerson() {
    return maxPerson;
  }
}
