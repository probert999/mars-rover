package com.probert999.marsrover.testhelper;

import com.probert999.marsrover.model.NASACapcom;

public class NASACapcomStub extends NASACapcom {

    public boolean isValidMove(String roverId, int xCoordinate, int yCoordinate)
    {
        return (xCoordinate >=0 && yCoordinate >= 0);
    };

    public String getStatusReport()
    {
        return "0 0 N";
    };

    public String processInstruction(String instruction)
    {
        return "Processed instruction";
    };

}
