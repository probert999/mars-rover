package com.probert999.marsrover.service;


import com.probert999.marsrover.model.NASACapcom;

public class NASACapcomService extends NASACapcom {

    public boolean isValidMove(String roverId, int xCoordinate, int yCoordinate)
    {
        return true;
    }

    public String getStatus()
    {
        return "0 0 N";
    };

    public void processInstruction(String instruction)
    {};
}
