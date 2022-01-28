package com.probert999.marsrover.model;

public interface PlateauInterface {

  boolean isValidCoordinate(int xCoordinate, int yCoordinate);

  String getPlateauId();

  String getPlateauDimensions();
}
