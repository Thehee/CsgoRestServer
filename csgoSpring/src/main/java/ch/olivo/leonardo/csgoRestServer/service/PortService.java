package ch.olivo.leonardo.csgoRestServer.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.stereotype.Component;

@Component
public class PortService {

  /**
   * This method makes a new instance of the port COM5.
   * It checks if there is already an instance of a port before it creates a new one.
   * @param comPort to check if there is already an instance
   * @return Instanced Port
   */
  public SerialPort createPort(SerialPort comPort) {
    if (comPort != null) {
      return comPort;
    }
    // print out all port names. I have only one (COM5).
    for (String portName : SerialPortList.getPortNames()) {
      System.out.println(portName);
    }

    // new SerialPort which is COM5 in my case
    return new SerialPort("COM5");
  }

  /**
   * This method tries to open a port.
   * @param comPort the port to open
   * @return returns the open port
   */
  public SerialPort openPort(SerialPort comPort) {

    if (!comPort.isOpened()) {
      try {
        comPort.openPort();
        comPort.setParams(9600,  8, 1, 0);
      } catch (SerialPortException e) {
        e.printStackTrace();
      }
    }

    return comPort;
  }

  /**
   * This method writes a string, through a port.
   * @param msg message to write
   * @param comPort port to write the msg through
   * @return Answer from Arduino
   */
  public byte[] writeString(byte[] msg, SerialPort comPort) {
    try {
      // try to send msg over the port.
      comPort.writeBytes(msg);

      return comPort.readBytes(msg.length + 1);
    } catch (SerialPortException e) {
      e.printStackTrace();
    }
    return null;
  }
}
