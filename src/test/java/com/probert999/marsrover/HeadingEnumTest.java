package com.probert999.marsrover;

import com.probert999.marsrover.model.DirectionEnum;
import com.probert999.marsrover.model.HeadingEnum;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadingEnumTest {

    @ParameterizedTest
    @MethodSource("spinLeftTestData")
    void shouldReturnCorrectHeadingAfterSpinningLeft(
            HeadingEnum currentHeading, HeadingEnum expectedValue) {
            assertEquals(expectedValue, currentHeading.getNewHeading(DirectionEnum.LEFT));
    }

    @ParameterizedTest
    @MethodSource("spinRightTestData")
    void shouldReturnCorrectHeadingAfterSpinningRight(
            HeadingEnum currentHeading, HeadingEnum expectedValue) {
        assertEquals(expectedValue, currentHeading.getNewHeading(DirectionEnum.RIGHT));
    }

    public static Stream<Arguments> spinLeftTestData() {
        return Stream.of(
                Arguments.of(HeadingEnum.NORTH, HeadingEnum.WEST),
                Arguments.of(HeadingEnum.WEST, HeadingEnum.SOUTH),
                Arguments.of(HeadingEnum.SOUTH, HeadingEnum.EAST),
                Arguments.of(HeadingEnum.EAST, HeadingEnum.NORTH));
    }

    public static Stream<Arguments> spinRightTestData() {
        return Stream.of(
                Arguments.of(HeadingEnum.NORTH, HeadingEnum.EAST),
                Arguments.of(HeadingEnum.EAST, HeadingEnum.SOUTH),
                Arguments.of(HeadingEnum.SOUTH, HeadingEnum.WEST),
                Arguments.of(HeadingEnum.WEST, HeadingEnum.NORTH));
    }

}
