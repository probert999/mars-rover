package com.probert999.marsrover.model;

import java.text.MessageFormat;

public class QuadPlateau extends Plateau {

  private final int xMaximum;
  private final int yMaximum;
  private final boolean mapEnabled;
  private QuadPlateauMap plateauMap = null;
  private final static int MAX_SIZE = 9999999;

  public QuadPlateau(String id, int xMaximum, int yMaximum, boolean mapEnabled) {

    if (xMaximum < 0 || yMaximum < 0) {
      throw new IllegalArgumentException("Negative coordinates not allowed");
    }

    if (xMaximum > MAX_SIZE || yMaximum > MAX_SIZE) {
      throw new IllegalArgumentException("Coordinates greater than " + MAX_SIZE);
    }


    this.xMaximum = xMaximum;
    this.yMaximum = yMaximum;
    this.id = id;
    this.dimensions = MessageFormat.format("({0},{1})", xMaximum, yMaximum);
    this.mapEnabled = mapEnabled;

    if (mapEnabled)
    {
      plateauMap = new QuadPlateauMap(id, xMaximum, yMaximum);
    }
  }

  public boolean isValidCoordinate(int xCoordinate, int yCoordinate) {
    boolean validCoordinate =
        ((xCoordinate >= 0 && xCoordinate <= xMaximum)
            && (yCoordinate >= 0 && yCoordinate <= yMaximum));

    if (validCoordinate) {
      // check not space is not already occupied
      RoverDetails checkRovers =
          rovers.stream()
              .filter(r -> r.getXPosition() == xCoordinate && r.getYPosition() == yCoordinate)
              .findFirst()
              .orElse(null);

      if (checkRovers != null) {
        validCoordinate = false;
      }
    }

    return validCoordinate;
  }

  public void showMap()
  {
    if (!mapEnabled)
      throw new IllegalStateException("Map feature not enabled");

    plateauMap.show(rovers);
  }

  public void hideMap(boolean finish)
  {
    if (mapEnabled)
      plateauMap.hide(finish);
  }

  public boolean isMapVisible()
  {
    return mapEnabled && plateauMap.isMapVisible();
  }
}
