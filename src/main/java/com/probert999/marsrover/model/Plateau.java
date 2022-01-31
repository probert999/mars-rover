package com.probert999.marsrover.model;

import java.util.ArrayList;

public abstract class Plateau implements PlateauInterface {

  protected String id;
  protected String dimensions;

  protected ArrayList<RoverDetails> rovers = new ArrayList<>();

  public String getId() {
    return this.id;
  }

  public String getDimensions() {
    return this.dimensions;
  }

  public boolean storeRoverPosition(String roverId, int xCoordinate, int yCoordinate, char heading) {
    boolean updateSuccess = false;

    RoverDetails findRover = rovers.stream().filter(r -> r.getRoverName() == roverId).findFirst().orElse(null);
    if (findRover == null) {
      if (isValidCoordinate(xCoordinate, yCoordinate)) {
        RoverDetails newRover = new RoverDetails(roverId, xCoordinate, yCoordinate, heading);
        rovers.add(newRover);
        updateSuccess = true;
      }
    } else {
      if (findRover.getXPosition() != xCoordinate && findRover.getYPosition() != yCoordinate) {
        // Rover moving to new space
        if (isValidCoordinate(xCoordinate, yCoordinate)) {
          findRover.updatePosition(xCoordinate, yCoordinate, heading);
          updateSuccess = true;
        }
      } else {
        // Just an update to the heading
        findRover.updatePosition(xCoordinate, yCoordinate, heading);
        updateSuccess = true;
      }
    }

    return updateSuccess;
  };

  abstract public void showMap();

  abstract public void hideMap(boolean finish);

  abstract public boolean isMapVisible();
}
