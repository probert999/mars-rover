package com.probert999.marsrover.app.model;

public class QuadPlateau extends Plateau {

    private int xMaximum;
    private int yMaximum;

    public QuadPlateau(String plateauId, int xMaximum, int yMaximum)
    {
        this.plateauId = plateauId;
    }

  public boolean isValidCoordinate(int x, int y) {
    return true;
    }

}
