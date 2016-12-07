package com.mygdx.game;

import java.io.InputStream;
import gnu.io.*;

public class SerialTest {
	  public static void main(String[] args) throws Exception
	    {
	        String dev = "/dev/ttyUSB1";
	 
	        System.out.println("Monitoring serial stream on " + dev);
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
	 
	                InputStream in = serialPort.getInputStream();
	                while (true) 
	                {
	                    int data = in.read();
	                    if (data < 0) break;
	                    System.out.print((char)data);
	                }
	            }
	        }
	    }
}
