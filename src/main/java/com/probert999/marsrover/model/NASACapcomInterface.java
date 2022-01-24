package com.probert999.marsrover.model;

public interface NASACapcomInterface {

    public boolean isValidMove(String roverId, int xCoordinate, int yCoordinate);
    public String getStatusReport();
    public void processInstruction(String instruction);

}
