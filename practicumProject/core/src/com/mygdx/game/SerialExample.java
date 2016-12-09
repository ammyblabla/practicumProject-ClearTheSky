package com.mygdx.game;

import java.io.*;
import gnu.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialExample implements Runnable{
  private static int accel = 0;
  private static float piezo = 0;
  private static String s = "";
  private static String accelString = "";
  private static String piezoString = "";
  private static BufferedReader reader;
  private static InputStream in;
  
  public static void setup() throws Exception
  {
    String dev = "/dev/ttyUSB0";

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
        in = serialPort.getInputStream();
        while(true){
        	readBufferLine();
//        readInputStream();
        }
      }
    }
  }
  public static void readBufferLine() {
      try {
    	  if(reader.ready()) 
    		  s = reader.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
      String tmp = s;
      split(tmp);
  }
  private static void split(String input){
//	  System.out.println(input);
	  String[] fields = input.split(",|\\ ",-1);
	  if(fields.length==2){
	      accelString = fields[0];
	      piezoString = fields[1];
	  }    
  }
  public static void readInputStream() {
	int data;
	try {
		data = in.read();
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
      if (data < 0) 
    	  return;
      System.out.print((char)data);
  }
  
  public static int getAccel() {
	if(isCanParse(accelString)){
	  accel =   Integer.parseInt(accelString);
  	}
//	System.out.println(accel);
	return accel;
  }
  
  public static float getPiezo() {
    if(isCanParse(piezoString))
    	piezo = Float.parseFloat(piezoString);
    return piezo;
  }
  
  private static boolean isCanParse(String input) {
	  if(input.length() == 0)
		  return false;
	  String[] delimiters = {" ", "0-"};
	  for(int i=0; i<delimiters.length; i++){
		for(int j=0; j<input.length(); j++){
			if(delimiters[i].charAt(0) == input.charAt(j)){
//				  System.out.println(input.length());
				for(int k=1; k<delimiters.length; k++)
				{
					if(delimiters[i].charAt(k) != input.charAt(j)){
						continue;
					}
					if(delimiters[i].charAt(k) == input.charAt(input.length()-1)){
						return false;
					}
						
				}
				
			}
		}
	  }
	  return true;
  }
	@Override
	public void run() {
		try {
	        setup();
	    } catch (Exception ex) {
	        Logger.getLogger(SerialExample.class.getName()).log(Level.SEVERE, null, ex);
	    }
		
	}
}
