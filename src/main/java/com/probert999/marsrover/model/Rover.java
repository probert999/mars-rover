package com.probert999.marsrover.model;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;

public abstract class Rover implements Navigator {

  protected NASACapcom capcom;
  protected Plateau plateau;
  protected String roverId;
  protected int xPosition;
  protected int yPosition;
  protected HeadingEnum currentHeading;

  public String getId() {
    return roverId;
  }

  public Plateau getPlateau() { return plateau; }

  public String getLocation()
  {
    return MessageFormat.format("{0} {1} {2}",xPosition, yPosition, currentHeading.getHeadingInitial());
  }

  public void spin(NASACapcom callingCapcom, DirectionEnum spinDirection)
  {
    if (callingCapcom != capcom)
    {
      throw new IllegalStateException("Only owning Capcom can request spin");
    }
    currentHeading = currentHeading.getNewHeading(spinDirection);
  }

  public void move(NASACapcom callingCapcom)  {
    if (callingCapcom != capcom)
    {
      throw new IllegalStateException("Only owning Capcom can request move");
    }

    int newXPosition = xPosition;
    int newYPosition = yPosition;

    switch (currentHeading) {
      case NORTH -> newYPosition++;
      case SOUTH -> newYPosition--;
      case EAST -> newXPosition++;
      case WEST -> newXPosition--;
    }

    if (plateau.isValidCoordinate(newXPosition, newYPosition)) {
      boolean updateSuccess = plateau.storeRoverPosition(roverId, newXPosition, newYPosition);
      if (!updateSuccess)
      {
        throw new IllegalStateException("Unable to store updated position");
      }
      xPosition = newXPosition;
      yPosition = newYPosition;
    }
    else {
      throw new IllegalStateException("Invalid move detected - check position");
    }

  }

}
