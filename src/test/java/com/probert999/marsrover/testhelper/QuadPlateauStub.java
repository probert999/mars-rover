package com.probert999.marsrover.testhelper;

import com.probert999.marsrover.model.Plateau;

public class QuadPlateauStub extends Plateau {

    boolean mapVisible = false;
    @Override
    public boolean isValidCoordinate(int xCoordinate, int yCoordinate) {
        return (xCoordinate >=0 && yCoordinate >= 0) && (xCoordinate <= 5 && yCoordinate <= 5);
    }

    @Override
    public boolean storeRoverPosition(String roverId, int xCoordinate, int yCoordinate) {
        return (xCoordinate >=1 && yCoordinate >= 0) && (xCoordinate <= 5 && yCoordinate <= 5);
    }

    @Override
    public void showMap() {
        mapVisible = true;
    }

    @Override
    public void hideMap(boolean finish) {
        mapVisible = false;
    }

    @Override
    public boolean isMapVisible() {
        return mapVisible;
    }

}
