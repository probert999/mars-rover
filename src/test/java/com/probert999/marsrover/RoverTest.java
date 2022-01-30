package com.probert999.marsrover;

import com.probert999.marsrover.model.DirectionEnum;
import com.probert999.marsrover.model.HeadingEnum;
import com.probert999.marsrover.model.SurfaceRover;
import com.probert999.marsrover.testhelper.NASACapcomStub;
import com.probert999.marsrover.testhelper.QuadPlateauStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {

  private NASACapcomStub capcom;
  private SurfaceRover testRover;
  private QuadPlateauStub plateau;

  @BeforeEach
  public void setup() {
    capcom = new NASACapcomStub();
    plateau = new QuadPlateauStub();
    testRover = new SurfaceRover(capcom, "TestRover", plateau,2, 2, HeadingEnum.NORTH);
  }

  @Test
  public void shouldReturnCorrectRoverId() {
    String testRoverId = "TestRover";
    SurfaceRover firstRover = new SurfaceRover(capcom, testRoverId, plateau,2, 2, HeadingEnum.NORTH);
    assertEquals(testRoverId, firstRover.getId());
  }

  @Test
  public void shouldReturnCorrectPlateau() {
    String testRoverId = "TestRover";
    SurfaceRover firstRover = new SurfaceRover(capcom, testRoverId, plateau,2, 2, HeadingEnum.NORTH);
    assertEquals(plateau, firstRover.getPlateau());
  }

  @Test
  public void shouldBeAbleToGetLocation() {
    assertEquals("2 2 N", testRover.getLocation());
  }

  @Test
  public void shouldBeAbleToSpinLeft() {
    testRover.spin(DirectionEnum.LEFT);
    assertEquals("2 2 W", testRover.getLocation());
  }

  @Test
  public void shouldBeAbleToSpinRight() {
    testRover.spin(DirectionEnum.RIGHT);
    assertEquals("2 2 E", testRover.getLocation());
  }


  @ParameterizedTest
  @MethodSource("validMoveTestData")
  public void shouldBeAbleToMoveToValidAndFreeGridReference(HeadingEnum heading, String expectedResult) {
    testRover = new SurfaceRover(capcom, "TestRover", plateau,2, 2, heading);
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
    testRover = new SurfaceRover(capcom, "TestRover", plateau,5, 5, HeadingEnum.NORTH);
    assertThrows(IllegalStateException.class, () -> testRover.move());
    assertEquals("5 5 N", testRover.getLocation());
  }

  @Test
  public void shouldNotBeAbleToStoreInvalidPosition(){
    assertThrows(IllegalStateException.class, () -> new SurfaceRover(capcom, "TestRover", plateau,-1, 0, HeadingEnum.SOUTH));
  }

  @Test
  public void shouldHandlePositionStoreFailure(){
    testRover = new SurfaceRover(capcom, "ROVER-FAIL", plateau,1, 4, HeadingEnum.WEST);
    assertThrows(IllegalStateException.class, () -> testRover.move());
  }


}
