package com.probert999.marsrover.app;

import com.probert999.marsrover.service.NASACapcomService;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

  private static final String CMD_FINISH = "FINISH";
  private static final String CMD_PLATEAU_LIST = "LIST PLATEAUS";
  private static final String CMD_ROVER_LIST = "LIST ROVERS";

  public static void main(String[] args) {
    NASACapcomService capcom = new NASACapcomService();
    start(capcom, System.in, args);
  }

  public static String start(NASACapcomService capcom, InputStream ins, String[] args)  {

    System.out.println("\n* NASA Capcom for Mars Rover *");

    Scanner in;
    String statusReport = null;

    if (args.length == 0)
    {
      // process console
      in = new Scanner(ins);
      statusReport = processLoop(capcom, in, false);
    }
    else
    {
      // process file
      File file = new File(args[0]);
      try {
        in = new Scanner(file);
        statusReport = processLoop(capcom, in, true);
        in.close();
      }
      catch (Exception e)  {
        System.out.println("File not found: " + args[0]);
      }
    }
    System.out.println("\n**** Final Status Report ****\n" + statusReport);
    return statusReport;
  }

  private static String processLoop(NASACapcomService capcom, Scanner in, boolean fromFile)
  {
    String instruction;
    boolean processing = true;
    while (processing) {
      try {
        if (!fromFile) {
          System.out.println("\n**** Awaiting instruction ****");
        }

        instruction = in.nextLine().toUpperCase();
        if (fromFile) {
          System.out.println("\n" + instruction);
        }

        switch (instruction)
        {
          case CMD_PLATEAU_LIST -> System.out.println(capcom.getPlateauList());
          case CMD_ROVER_LIST -> System.out.println(capcom.getRoverList());
          case CMD_FINISH -> processing = false;
          default ->  {System.out.println(capcom.processInstruction(instruction));}
        }

      } catch (Exception e)  {
        System.out.println(e.getMessage());
      }
    }
    return capcom.getStatusReport();
  }
}
