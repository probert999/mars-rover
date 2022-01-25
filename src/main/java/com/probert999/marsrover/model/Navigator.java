package com.probert999.marsrover.model;

public interface Navigator {

    public void setPosition(NASACapcom capcom, int xCoordinate, int yCoordinate, HeadingEnum heading);

    public String getLocation();

    public void spin(DirectionEnum spinDirection);

    public void move();
}
