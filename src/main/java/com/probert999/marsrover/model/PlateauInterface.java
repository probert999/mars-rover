package com.probert999.marsrover.model;

public interface PlateauInterface {

  boolean isValidCoordinate(int xCoordinate, int yCoordinate);

  String getId();

  String getDimensions();

  boolean storeRoverPosition(String roverId, int xCoordinate, int yCoordinate, char heading);

}
