package com.probert999.marsrover.model;

public interface Navigator {

  String getLocation();

  void spin(DirectionEnum spinDirection);

  void move();
}
