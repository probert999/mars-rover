package com.probert999.marsrover.model;

interface NASACapcomInterface {

  String getStatusReport();

  String processInstruction(String instruction);
}
