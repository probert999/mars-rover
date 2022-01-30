package com.probert999.marsrover.model;

import java.text.MessageFormat;

public class QuadPlateau extends Plateau {

  private final int xMaximum;
  private final int yMaximum;

  public QuadPlateau(String id, int xMaximum, int yMaximum) {

    if (xMaximum < 0 || yMaximum < 0) {
      throw new IllegalArgumentException("Negative coordinates not allowed");
    }

    this.xMaximum = xMaximum;
    this.yMaximum = yMaximum;
    this.id = id;
    this.dimensions = MessageFormat.format("({0},{1})", xMaximum, yMaximum);
  }

  public boolean isValidCoordinate(int xCoordinate, int yCoordinate) {
    boolean validCoordinate =
        ((xCoordinate >= 0 && xCoordinate <= xMaximum)
            && (yCoordinate >= 0 && yCoordinate <= yMaximum));

    if (validCoordinate) {
      // check not space is not already occupied
      RoverDetails checkRovers =
          rovers.stream()
              .filter(r -> r.xPosition == xCoordinate && r.yPosition == yCoordinate)
              .findFirst()
              .orElse(null);

      if (checkRovers != null) {
        validCoordinate = false;
      }
    }

    return validCoordinate;
  }
}
