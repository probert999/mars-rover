package com.probert999.marsrover.model;

public class RoverDetails {

  private final String roverName;
  private int xPosition;
  private int yPosition;
  private char heading;

  public RoverDetails(String roverName, int xPosition, int yPosition, char heading) {
    this.roverName = roverName;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.heading = heading;
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

  public char getHeading() {
    return heading;
  }

  public void updatePosition(int xPosition, int yPosition, char heading)
  {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }
}
