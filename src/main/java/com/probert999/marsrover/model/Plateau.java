package com.probert999.marsrover.model;

public abstract class Plateau implements PlateauInterface {

  protected String plateauId;
  protected String plateauDimensions;

  public String getPlateauId() {
    return this.plateauId;
  }

  public String getPlateauDimensions() {
    return this.plateauDimensions;
  }
}
