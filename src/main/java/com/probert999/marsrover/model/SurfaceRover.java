package com.probert999.marsrover.model;

public class SurfaceRover extends Rover {

  public SurfaceRover(NASACapcom capcom, String roverId, Plateau plateau, int xCoordinate, int yCoordinate, HeadingEnum heading) {
    this.capcom = capcom;
    this.roverId = roverId;
    this.plateau = plateau;
    this.xPosition = xCoordinate;
    this.yPosition = yCoordinate;
    this.currentHeading = heading;

    boolean updateSuccess = plateau.storeRoverPosition(roverId, xCoordinate, yCoordinate, heading.getHeadingInitial());
    if (!updateSuccess)
    {
      throw new IllegalStateException("Unable to set position on plateau");
    }
  }
}
