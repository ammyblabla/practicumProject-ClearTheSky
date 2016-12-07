import java.io.*;
import gnu.io.*;

public class SerialExample {
  static double accel;
  static double piezo;
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        while (true)
        {
          String s = reader.readLine();
          // String[] fields = s.split(",");
          // System.out.println(fields[0]);
          //
          // accel = Double.parseDouble(fields[0]);
          // piezo = Double.parseDouble(fields[1]);
          // System.out.println(accel + "," + piezo);

          System.out.println(s);
        }
      }
    }
  }
  public double getAccel() {
    return accel;
  }
  public double getPiezo() {
    return piezo;
  }
}
