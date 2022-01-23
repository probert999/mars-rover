package com.probert999.marsrover.model;

import java.text.MessageFormat;

public abstract class Rover implements Navigator {

  protected NASACapcom capcom;
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
    if (currentHeading == null)
    {
      throw new IllegalStateException("Heading has not yet been set");
    }
    currentHeading = currentHeading.getNewHeading(spinDirection);
  }

  public void move()
  {
    int newXPosition = xPosition;
    int newYPosition = yPosition;
    switch (currentHeading)
    {
      case NORTH -> newYPosition++;
      case SOUTH -> newYPosition--;
      case EAST -> newXPosition++;
      case WEST -> newXPosition--;
    }

    if (capcom.isValidMove(roverId, newXPosition, newYPosition))
    {
      xPosition = newXPosition;
      yPosition = newYPosition;
    }
    else {
      throw new IllegalStateException("Invalid move - position unchanged");
    }

  }

}
