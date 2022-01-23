package com.probert999.marsrover;

import com.probert999.marsrover.model.DirectionEnum;
import com.probert999.marsrover.model.HeadingEnum;
import com.probert999.marsrover.model.SurfaceRover;
import com.probert999.marsrover.testhelper.NASACapcomStub;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class RoverTest {

  private NASACapcomStub capcom;
  private SurfaceRover testRover;

  @BeforeEach
  public void setup() {
    capcom = new NASACapcomStub();
    testRover = new SurfaceRover(capcom,"testRover1");
  }

  @Test
  public void shouldReturnCorrectRoverId() {
    String testRoverId = "TestRover";
    SurfaceRover firstRover = new SurfaceRover(capcom, testRoverId);
    assertEquals(testRoverId, firstRover.getRoverId());
  }

  @Test
  public void shouldBeAbleToSetValidCoordinates() {
    assertEquals(true, testRover.setPosition(0, 0, HeadingEnum.NORTH));
  }

  @Test
  public void shouldNotBeAbleToSetNegativeXCoordinates() {
    assertEquals(false, testRover.setPosition(-1, 0, HeadingEnum.NORTH));
  }

  @Test
  public void shouldNotBeAbleToSetNegativeYCoordinates() {
    assertEquals(false, testRover.setPosition(0, -1, HeadingEnum.NORTH));
  }

  @Test
  public void shouldBeAbleToGetLocation() {
    testRover.setPosition(0, 0, HeadingEnum.NORTH);
    assertEquals("0 0 N", testRover.getLocation());
  }

  @Test
  public void shouldNotBeAbleToGetLocationIfItHasNotBeenSet() {
    assertThrows(IllegalStateException.class, () -> testRover.getLocation());
  }

  @Test
  public void shouldBeAbleToSpinLeft() {
    testRover.setPosition(0, 0, HeadingEnum.NORTH);
    testRover.spin(DirectionEnum.LEFT);
    assertEquals("0 0 W", testRover.getLocation());
  }

  @Test
  public void shouldBeAbleToSpinRight() {
    testRover.setPosition(0, 0, HeadingEnum.NORTH);
    testRover.spin(DirectionEnum.RIGHT);
    assertEquals("0 0 E", testRover.getLocation());
  }

  @Test
  public void shouldNotBeAbleToSpinIfHeadingHasNotBeenSet() {
    assertThrows(IllegalStateException.class, () -> testRover.spin(DirectionEnum.RIGHT));
  }

  @ParameterizedTest
  @MethodSource("validMoveTestData")
  public void shouldBeAbleToMoveToValidAndFreeGridReference(HeadingEnum heading, String expectedResult) {
    testRover.setPosition(2, 2, heading);
    testRover.move();
    assertEquals(expectedResult, testRover.getLocation());
  }

  public static Stream<Arguments> validMoveTestData() {
    return Stream.of(
            Arguments.of(HeadingEnum.NORTH, "2 3 N"),
            Arguments.of(HeadingEnum.EAST, "3 2 E"),
            Arguments.of(HeadingEnum.SOUTH, "2 1 S"),
            Arguments.of(HeadingEnum.WEST, "1 2 W"));
  }

  @Test
  public void shouldNotMoveToInvalidSpace() {
    testRover.setPosition(0, 0, HeadingEnum.SOUTH);

    assertThrows(IllegalStateException.class, () -> testRover.move());
    assertEquals("0 0 S", testRover.getLocation());
  }
}
