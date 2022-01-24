package com.probert999.marsrover;

import com.probert999.marsrover.model.HeadingEnum;
import com.probert999.marsrover.model.InstructionTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructionTypeEnumTest {

    @Test
    public void shouldRecogniseACreatePlateauInstruction()
    {
        String instruction = "5 5";

        assertEquals(InstructionTypeEnum.CREATE_PLATEAU, InstructionTypeEnum.getInstructionType(instruction));
    }

    @Test
    public void shouldRecogniseACreateRoverInstruction()
    {
        String instruction = "5 5 N";

        assertEquals(InstructionTypeEnum.CREATE_ROVER, InstructionTypeEnum.getInstructionType(instruction));
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

    @Test
    public void shouldRecogniseAnInvalidInstruction()
    {
        String instruction = "5 L N";

        assertEquals(InstructionTypeEnum.INVALID_INSTRUCTION, InstructionTypeEnum.getInstructionType(instruction));
    }

}
