package com.probert999.marsrover.model;

import java.text.MessageFormat;

public abstract class Rover implements Navigator {

  protected String roverId;
  protected int xPosition;
  protected int yPosition;
  protected HeadingEnum currentHeading;

  public String getRoverId() {
    return roverId;
  }

  public boolean setPosition(int xCoordinate, int yCoordinate, HeadingEnum heading)
  {
    boolean positionSet = false;
    if (xCoordinate >=0 && yCoordinate >= 0)
    {
      this.xPosition = xCoordinate;
      this.yPosition = yCoordinate;
      this.currentHeading = heading;
      positionSet = true;
    }
    return positionSet;
  }

  public String getLocation()
  {
    if (currentHeading == null)
    {
      throw new IllegalStateException("Position has not yet been set");
    }
    return MessageFormat.format("{0} {1} {2}",xPosition, yPosition, currentHeading.getHeadingInitial());
  }

  public void spin(DirectionEnum spinDirection)
  {
    currentHeading = currentHeading.getNewHeading(spinDirection);
  }

}
