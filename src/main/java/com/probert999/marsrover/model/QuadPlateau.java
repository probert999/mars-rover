package com.probert999.marsrover.model;

import java.text.MessageFormat;

public class QuadPlateau extends Plateau {

  private final int xMaximum;
  private final int yMaximum;

  public QuadPlateau(String plateauId, int xMaximum, int yMaximum) {

    if (xMaximum < 0 || yMaximum < 0) {
      throw new IllegalArgumentException("Negative coordinates not allowed");
    }

    this.xMaximum = xMaximum;
    this.yMaximum = yMaximum;
    this.plateauId = plateauId;
    this.plateauDimensions =  MessageFormat.format("({0},{1})", xMaximum, yMaximum);
  }

  public boolean isValidCoordinate(int xCoordinate, int yCoordinate) {
    return ((xCoordinate >= 0 && xCoordinate <= xMaximum)
        && (yCoordinate >= 0 && yCoordinate <= yMaximum));
  }
}
