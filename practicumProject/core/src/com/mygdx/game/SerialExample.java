package com.mygdx.game;

import java.io.*;
import gnu.io.*;

public class SerialExample {
  private static double accel = 0;
  private static double piezo = 0;
  private static String s = "";
  private static String accelString = "";
  private static String piezoString = "";
  private static BufferedReader reader;
  
  public static void main(String[] args) throws Exception
  {
    String dev = "/dev/ttyUSB2";

    CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(dev);
    if (portIdentifier.isCurrentlyOwned())
    {
      System.out.println( "Error: Port is currently in use" );
    }
    else
    {
      int timeout = 2000;
      CommPort commPort = portIdentifier.open("serial",timeout);

      if (commPort instanceof SerialPort)
      {
        SerialPort serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(9600,
            SerialPort.DATABITS_8,
            SerialPort.STOPBITS_1,
            SerialPort.PARITY_NONE );

        reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        while (true)
        {
//          String s = reader.readLine();
//          String[] fields = s.split(",");
////          System.out.println(fields[1]);
//        System.out.println(s);
//          
//          accelString = fields[0];
//          piezoString = fields[1];
////          accel = Float.parseFloat(fields[0]);
////          piezo = Float.parseFloat(fields[1]);
//          System.out.println(accelString + "," + piezoString);
        	readBufferLine();

        }
      }
    }
  }
  public static void readBufferLine() {
      try {
		s = reader.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
      String[] fields = s.split(",");
//      System.out.println(fields[1]);
    System.out.println(s);
      
//      accelString = fields[0];
//      piezoString = fields[1];
      
//      accel = Float.parseFloat(fields[0]);
//      piezo = Float.parseFloat(fields[1]);
//      System.out.println(accelString + "," + piezoString);	  
  }
  
  public String getAccel() {
    return accelString;
  }
  
  public String getPiezo() {
    return piezoString;
  }
}
