package com.probert999.marsrover.model;

public enum InstructionTypeEnum {
  CREATE_PLATEAU("Create Plateau"),
  CREATE_ROVER("Create Rover"),
  MOVE_ROVER("Move Rover"),
  SWITCH_PLATEAU("Switch Plateau"),
  SWITCH_ROVER("Switch Rover"),
  SHOW_MAP("Show Map"),
  HIDE_MAP("Hide Map"),
  INVALID_INSTRUCTION("Invalid Instruction");

  InstructionTypeEnum(String instructionText) {
  }

  public static InstructionTypeEnum getInstructionType(String instruction) {

    InstructionTypeEnum instructionType = INVALID_INSTRUCTION;

    if (instruction.matches("[0-9]{1,7} [0-9]{1,7}")) {
      instructionType = CREATE_PLATEAU;
    } else if (instruction.matches("[0-9]{1,7} [0-9]{1,7} [NSEW]")) {
      instructionType = CREATE_ROVER;
    } else if (instruction.matches("[LRM]+")) {
      instructionType = MOVE_ROVER;
    }  else if (instruction.matches("SWITCH PLATEAU-[0-9]+")) {
       instructionType = SWITCH_PLATEAU;
    }  else if (instruction.matches("SWITCH ROVER-[0-9]+")) {
        instructionType = SWITCH_ROVER;
    }  else if (instruction.matches("SHOW MAP")) {
        instructionType = SHOW_MAP;
    } else if (instruction.matches("HIDE MAP")) {
      instructionType = HIDE_MAP;
    }
    return instructionType;
  }
}
