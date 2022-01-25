package com.probert999.marsrover.app;

import com.probert999.marsrover.service.NASACapcomService;

import java.util.Locale;
import java.util.Scanner;

public class Main {

  private static final String FINISH_COMMAND = "finish";

  public static void main(String[] args) {
    NASACapcomService capcom = new NASACapcomService();

    System.out.println("\n* NASA Capcom for Mars Rover *\n");
    System.out.println("*** Awaiting instruction ***\n");

    Scanner in = new Scanner(System.in);

    String instruction = "";
    boolean processing = true;
    while (processing) {
      try {
        instruction = in.nextLine();
        if (!(instruction.equalsIgnoreCase(FINISH_COMMAND))) {
          capcom.processInstruction(instruction);
        } else {
          processing = false;
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println("\n*** Final Status Report ***\n");
    System.out.println(capcom.getStatusReport());
  }
}
