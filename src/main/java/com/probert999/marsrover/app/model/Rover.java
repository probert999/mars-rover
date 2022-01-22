package com.probert999.marsrover.app.model;

public abstract class Rover implements Navigator {

  protected String roverId;
  protected int xCoordinate;
  protected int yCoordinate;
  protected HeadingEnum currentHeading;

  public String getRoverId() {
    return roverId;
  }

  public boolean setPosition(int xCoordinate, int yCoordinate, HeadingEnum heading)
  {
    boolean positionSet = false;
    if (xCoordinate >=0 && yCoordinate >= 0)
    {
      this.xCoordinate = xCoordinate;
      this.yCoordinate = yCoordinate;
      this.currentHeading = heading;
      positionSet = true;
    }
    return positionSet;
  }

}
