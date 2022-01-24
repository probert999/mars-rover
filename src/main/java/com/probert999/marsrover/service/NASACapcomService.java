package com.probert999.marsrover.service;

import com.probert999.marsrover.model.HeadingEnum;
import com.probert999.marsrover.model.InstructionTypeEnum;
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
        InstructionTypeEnum instructionType = InstructionTypeEnum.getInstructionType(instruction);

        switch (instructionType)
        {
            case CREATE_PLATEAU -> {
                int xMaximum = Character.getNumericValue(instruction.charAt(0));
                int yMaximum = Character.getNumericValue(instruction.charAt(2));
                createPlateau(xMaximum, yMaximum);
                break;
            }
            case CREATE_ROVER -> {
                int xCoordinate = Character.getNumericValue(instruction.charAt(0));
                int yCoordinate = Character.getNumericValue(instruction.charAt(2));
                HeadingEnum heading = HeadingEnum.getByInitial(instruction.charAt(4));
                createRover(xCoordinate, yCoordinate, heading);
                break;
            }
            case MOVE_ROVER -> {
                break;
            }
            case INVALID_INSTRUCTION -> {

            }

        }

    };


}
