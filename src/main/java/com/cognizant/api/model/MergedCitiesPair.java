package com.cognizant.api.model;

public class MergedCitiesPair<Start, End> {

  private final Start start;
  private final End destination;

  public MergedCitiesPair(Start start, End end) {
    this.start = start;
    this.destination = end;
  }

  public Start getStart() { return start; }
  public End getDestination() { return destination; }

  @Override
  public int hashCode() { return start.hashCode() * destination.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MergedCitiesPair)) return false;
    MergedCitiesPair pairo = (MergedCitiesPair) o;
    return this.start.equals(pairo.getStart()) &&
        this.destination.equals(pairo.getDestination()) || this.destination.equals(pairo.getStart()) &&
        this.start.equals(pairo.getDestination());
  }
}
