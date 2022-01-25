package com.probert999.marsrover.model;

public interface NASACapcomInterface {

  boolean isValidMove(String roverId, int xCoordinate, int yCoordinate);

  String getStatusReport();

  void processInstruction(String instruction);
}
