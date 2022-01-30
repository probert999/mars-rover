package com.probert999.marsrover.model;

public interface NASACapcomInterface {

  String getStatusReport();

  String processInstruction(String instruction);
}
