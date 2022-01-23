package com.probert999.marsrover.testhelper;

import com.probert999.marsrover.model.NASACapcom;

public class NASACapcomStub extends NASACapcom {

    public boolean isValidMove(String roverId, int xCoordinate, int yCoordinate)
    {
        return (xCoordinate >=0 && yCoordinate >= 0);
    };

    public String getStatus()
    {
        return "0 0 N";
    };

    public void processInstruction(String instruction){};

}
