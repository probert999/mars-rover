package com.probert999.marsrover;

import com.probert999.marsrover.model.InstructionTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
    @MethodSource("validMoveInstructionTestData")
    public void shouldRecogniseValidRoverMoveInstructionsFromTestData(String instruction)
    {
        assertEquals(InstructionTypeEnum.MOVE_ROVER, InstructionTypeEnum.getInstructionType(instruction));
    }

    public static Stream<Arguments> validMoveInstructionTestData() {
        return Stream.of(
                Arguments.of("L"),
                Arguments.of("R"),
                Arguments.of("M"),
                Arguments.of("LL"),
                Arguments.of("RR"),
                Arguments.of("MM"),
                Arguments.of("LMLMLMLMM"),
                Arguments.of("MMRMMRMRRM")
                );
    }

    @ParameterizedTest
    @MethodSource("invalidInstructionTestData")
    public void shouldRejectAnInvalidInstructions(String instruction)
    {
        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

    public static Stream<Arguments> invalidInstructionTestData() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("10"),
                Arguments.of("10 L S"),
                Arguments.of("L 10 S"),
                Arguments.of("S 10 10"),
                Arguments.of("X")
        );
    }

}
