package com.probert999.marsrover.model;

public class RoverDetails {

  private final String roverName;
  private int xPosition;
  private int yPosition;

  public RoverDetails(String roverName, int xPosition, int yPosition) {
    this.roverName = roverName;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  public String getRoverName() {
    return roverName;
  }

  public int getXPosition() {
    return xPosition;
  }

  public int getYPosition() {
    return yPosition;
  }

  public void updatePosition(int xPosition, int yPosition)
  {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }
}
