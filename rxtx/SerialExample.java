import java.io.*;
import gnu.io.*;

public class SerialExample {

  public static void main(String[] args) throws Exception
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        while (true) 
        {
          String s = reader.readLine();
          String[] fields = s.split(" ");
          double value = Double.parseDouble(fields[1]);
          System.out.print(value);
          System.out.print(" ");
          System.out.println(value*2);
        }
      }
    }
  }
}
