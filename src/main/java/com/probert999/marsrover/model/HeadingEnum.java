package com.probert999.marsrover.model;

public enum HeadingEnum {
  NORTH ('N', "WEST", "EAST"),
  EAST ('E', "NORTH",  "SOUTH"),
  SOUTH ('S', "EAST", "WEST"),
  WEST ('W', "SOUTH", "NORTH");

  private final char headingInitial;
  private final String headingLeft;
  private final String headingRight;

  HeadingEnum(char headingInitial, String headingLeft, String headingRight) {
    this.headingInitial = headingInitial;
    this.headingLeft = headingLeft;
    this.headingRight = headingRight;
  }

  public char getHeadingInitial() {
    return this.headingInitial;
  }


  public HeadingEnum getNewHeading(DirectionEnum spinDirection)
  {
    HeadingEnum newHeading;
    if (spinDirection == DirectionEnum.LEFT) {
      newHeading = HeadingEnum.valueOf(this.headingLeft);
      }
    else {
      newHeading = HeadingEnum.valueOf(this.headingRight);
    }
    return newHeading;
  }
}
