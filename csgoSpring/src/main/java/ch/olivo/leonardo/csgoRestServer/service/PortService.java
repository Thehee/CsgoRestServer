package ch.olivo.leonardo.csgoRestServer.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PortService {

  private final byte START_BYTE = 0x7E;
  private final byte END_BYTE = 0x25;
  private final byte ESCAPE_BYTE = 0x3F;

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
        comPort.setParams(9600, 8, 1, 0);
      } catch (SerialPortException e) {
        e.printStackTrace();
      }
    }

    return comPort;
  }

  /**
   * This method writes a string, through a port.
   * @param msg     message to write
   * @param comPort port to write the msg through
   * @return Answer from Arduino
   */
  public byte[] writeString(String msg, SerialPort comPort) {

    // create a byte array with the msg
    byte[] escapedMsg = arrayListToByteArray(createMsg(msg));

    try {
      // try to send msg over the port.
      comPort.writeBytes(escapedMsg);
      return comPort.readBytes(escapedMsg.length);

    } catch (SerialPortException e) {
      e.printStackTrace();
    }
    return null;
  }

  private ArrayList<Byte> createMsg(String msg) {
    ArrayList<Byte> rawData = new ArrayList<>();
    ArrayList<Byte> escapedData = new ArrayList<>();
    byte[] byteMsg = msg.getBytes();

    for (byte stringByte : byteMsg) {
      rawData.add(stringByte);
    }

    escapedData.add(START_BYTE);
    escapedData.addAll(escapeRawData(rawData));
    byte length = (byte) (escapedData.size() - 1);
    escapedData.add(1, length);
    escapedData.add(END_BYTE);

    return escapedData;
  }

  private ArrayList<Byte> escapeRawData(ArrayList<Byte> rawData) {
    ArrayList<Byte> escapedData = new ArrayList<>();

    for (byte rawByte : rawData) {
      if (rawByte == START_BYTE || rawByte == END_BYTE || rawByte == ESCAPE_BYTE) {
        escapedData.add(ESCAPE_BYTE);
      }
      escapedData.add(rawByte);
    }

    return escapedData;
  }

  private byte[] arrayListToByteArray(ArrayList<Byte> arrayList) {
    byte[] bytes = new byte[arrayList.size()];

    for (int i = 0; i < arrayList.size(); i++) {
      bytes[i] = arrayList.get(i);
    }

    return bytes;
  }
}
