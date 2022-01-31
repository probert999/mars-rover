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

    if (isValidCoordinate(xCoordinate, yCoordinate)) {
      RoverDetails findRover = rovers.stream().filter(r -> r.getRoverName() == roverId).findFirst().orElse(null);

      if (findRover == null) {
        RoverDetails newRover = new RoverDetails(roverId, xCoordinate, yCoordinate, heading);
        rovers.add(newRover);
      } else {
        findRover.updatePosition(xCoordinate, yCoordinate, heading);
      }
      updateSuccess = true;
    }
    return updateSuccess;
  };

  abstract public void showMap();

  abstract public void hideMap(boolean finish);

  abstract public boolean isMapVisible();
}
