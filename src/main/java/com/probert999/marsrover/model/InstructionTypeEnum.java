package com.probert999.marsrover.model;

public enum InstructionTypeEnum {
  CREATE_PLATEAU("[0-9]{1,7} [0-9]{1,7}"),
  CREATE_ROVER("[0-9]{1,7} [0-9]{1,7} [NSEW]"),
  MOVE_ROVER("[LRM]+"),
  SWITCH_PLATEAU("SWITCH PLATEAU-[0-9]+"),
  SWITCH_ROVER("SWITCH ROVER-[0-9]+"),
  SHOW_MAP("SHOW MAP"),
  HIDE_MAP("HIDE MAP"),
  INVALID_INSTRUCTION("Invalid Instruction");

  private final String instructionText;

  InstructionTypeEnum(String instructionText) {
    this.instructionText = instructionText;
  }

  public static InstructionTypeEnum getInstructionType(String instruction) {

    InstructionTypeEnum instructionType = INVALID_INSTRUCTION;

    for (InstructionTypeEnum checkInstruction : InstructionTypeEnum.values()) {
      if (instruction.matches(checkInstruction.instructionText)) {
        instructionType = checkInstruction;
        break;
      }
    }
    return instructionType;
    }
}
