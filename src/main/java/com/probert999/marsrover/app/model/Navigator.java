package com.probert999.marsrover.app.model;

public interface Navigator {

    public boolean setPosition(int xCoordinate, int yCoordinate, HeadingEnum heading);

    public String getLocation();
}
