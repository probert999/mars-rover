package com.probert999.marsrover.model;

import java.util.ArrayList;

public abstract class Plateau implements PlateauInterface {

  class RoverDetails {

    String roverName;
    int xPosition;
    int yPosition;

    protected RoverDetails(String roverName, int xPosition, int yPosition) {
      this.roverName = roverName;
      this.xPosition = xPosition;
      this.yPosition = yPosition;
    }
  }

  protected String id;
  protected String dimensions;

  protected ArrayList<RoverDetails> rovers = new ArrayList<>();

  public String getId() {
    return this.id;
  }

  public String getDimensions() {
    return this.dimensions;
  }

  public boolean storeRoverPosition(String roverId, int xCoordinate, int yCoordinate)
  {
    boolean updateSuccess = false;

    if (isValidCoordinate(xCoordinate, yCoordinate)) {
      RoverDetails findRover =
          rovers.stream().filter(r -> r.roverName == roverId).findFirst().orElse(null);

      if (findRover == null) {
        RoverDetails newRover = new RoverDetails(roverId, xCoordinate, yCoordinate);
        rovers.add(newRover);
      } else {
        findRover.xPosition = xCoordinate;
        findRover.yPosition = yCoordinate;
      }
      updateSuccess = true;
    }
    return updateSuccess;
  };
}
