package com.probert999.marsrover.model;

public interface Navigator {

  void setPosition(NASACapcom capcom, int xCoordinate, int yCoordinate, HeadingEnum heading);

  String getLocation();

  void spin(DirectionEnum spinDirection);

  void move();
}
