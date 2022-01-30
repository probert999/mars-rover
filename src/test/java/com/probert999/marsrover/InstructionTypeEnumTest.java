package com.probert999.marsrover;

import com.probert999.marsrover.model.InstructionTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructionTypeEnumTest {

    @Test
    public void shouldRecogniseACreatePlateauInstructionUsingSingleDigits()
    {
        String instruction = "5 5";
        assertEquals(InstructionTypeEnum.CREATE_PLATEAU, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseACreatePlateauInstructionUsingDoubleDigits()
    {
        String instruction = "10 10";
        assertEquals(InstructionTypeEnum.CREATE_PLATEAU, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseACreatePlateauInstructionUsingMaxDigits()
    {
        String instruction = "1000000 1000000";
        assertEquals(InstructionTypeEnum.CREATE_PLATEAU, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRejectACreatePlateauInstructionUsingMoreThanMaxDigits()
    {
        String instruction = "10000000 10000000";
        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseACreateRoverInstruction()
    {
        String instruction = "5 5 N";
        assertEquals(InstructionTypeEnum.CREATE_ROVER, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseACreateRoverInstructionWithTwoDigitCoordinates()
    {
        String instruction = "50 50 N";
        assertEquals(InstructionTypeEnum.CREATE_ROVER, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseACreateRoverInstructionWithMaxDigitCoordinates()
    {
        String instruction = "9999999 9999999 N";
        assertEquals(InstructionTypeEnum.CREATE_ROVER, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRejectACreateRoverInstructionUsingMoreThanMaxDigits()
    {
        String instruction = "10000000 10000000 N";
        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

    @ParameterizedTest
    @ValueSource(strings = {"L","R","M","LL","RR","MM","LMLMLMLMM","MMRMMRMRRM"})
    public void shouldRecogniseValidRoverMoveInstructionsFromTestData(String instruction)
    {
        assertEquals(InstructionTypeEnum.MOVE_ROVER, InstructionTypeEnum.getInstructionType(instruction));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "10", "10 L S", "L 10 S", "S 10 10","X"})
    public void shouldRejectAnInvalidInstructions(String instruction)
    {
        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

    @ParameterizedTest
    @ValueSource(strings = {"SWITCH PLATEAU-1","SWITCH PLATEAU-10"})
    public void shouldRecogniseValidSwitchPlateauInstructionFromTestData(String instruction)
    {
        assertEquals(InstructionTypeEnum.SWITCH_PLATEAU, InstructionTypeEnum.getInstructionType(instruction));
    }

    @ParameterizedTest
    @ValueSource(strings = {"SWITCH PLATEAU","SWITCH PLATEAU-","SWITCH PLATEAU-X"})
    public void shouldRejectInvalidSwitchPlateauInstructionFromTestData(String instruction)
    {
        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

    @ParameterizedTest
    @ValueSource(strings = {"SWITCH ROVER-1","SWITCH ROVER-10"})
    public void shouldRecogniseValidSwitchRoverInstructionFromTestData(String instruction)
    {
        assertEquals(InstructionTypeEnum.SWITCH_ROVER, InstructionTypeEnum.getInstructionType(instruction));
    }

    @ParameterizedTest
    @ValueSource(strings = {"SWITCH ROVER","SWITCH ROVER-","SWITCH ROVER-X"})
    public void shouldRejectInvalidSwitchRoverInstructionFromTestData(String instruction)
    {
        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseValidShowMapInstruction()
    {
        String instruction = "SHOW MAP";
        assertEquals(InstructionTypeEnum.SHOW_MAP, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseValidHideMapInstruction()
    {
        String instruction = "HIDE MAP";
        assertEquals(InstructionTypeEnum.HIDE_MAP, InstructionTypeEnum.getInstructionType(instruction));
    }


}
