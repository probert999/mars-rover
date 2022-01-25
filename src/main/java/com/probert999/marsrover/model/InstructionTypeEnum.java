package com.probert999.marsrover.model;

public enum InstructionTypeEnum {
  CREATE_PLATEAU("Create Plateau"),
  CREATE_ROVER("Create Rover"),
  MOVE_ROVER("Move Rover"),
  INVALID_INSTRUCTION("Invalid Instruction");

  private final String instructionText;

  InstructionTypeEnum(String instructionText) {
    this.instructionText = instructionText;
  }

  public static InstructionTypeEnum getInstructionType(String instruction) {

    InstructionTypeEnum instructionType = INVALID_INSTRUCTION;

    if (instruction.matches("[0-9]{1,7} [0-9]{1,7}")) {
      instructionType = CREATE_PLATEAU;
    } else if (instruction.matches("[0-9]{1,7} [0-9]{1,7} [NSEW]")) {
      instructionType = CREATE_ROVER;
    } else if (instruction.matches("[LRM]+")) {
      instructionType = MOVE_ROVER;
    }

    return instructionType;
  }
}
