package com.probert999.marsrover.service;

import com.probert999.marsrover.model.HeadingEnum;
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
    {
        // Check for create Quad Plateau
        if (instruction.matches("[0-9] [0-9]"))
        {
            int xMaximum = Character.getNumericValue(instruction.charAt(0));
            int yMaximum = Character.getNumericValue(instruction.charAt(2));
            createPlateau(xMaximum, yMaximum);
        }

        // Check for create Rover
        if (instruction.matches("[0-9] [0-9] [NSEW]"))
        {
            int xCoordinate = Character.getNumericValue(instruction.charAt(0));
            int yCoordinate = Character.getNumericValue(instruction.charAt(2));
            HeadingEnum heading = HeadingEnum.getByInitial(instruction.charAt(4));
            createRover(xCoordinate, yCoordinate, heading);
        }
    };


}
