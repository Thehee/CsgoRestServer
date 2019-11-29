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
   * @param comPort to check if there is already an instance.
   * @return Instanced Port.
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
   * @param comPort the port to open.
   * @return returns the open port.
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
   * @param msg  message to write.
   * @param comPort port to write the msg through.
   * @return Answer from Arduino.
   */
  public byte[] writeString(String msg, SerialPort comPort) {

    // create a byte array with the msg
    byte[] escapedMsg = createMsg(msg);

    try {
      // try to send msg over the port.
      comPort.writeBytes(escapedMsg);
      return comPort.readBytes(escapedMsg.length);

    } catch (SerialPortException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * this method creates a byte array which fulfils a certain protocol .
   * @param msg the msg which should be converted to the byte array.
   * @return byte array which fulfils the protocol and includes the msg.
   */
  private byte[] createMsg(String msg) {
    ArrayList<Byte> rawData = new ArrayList<>();
    ArrayList<Byte> escapedData = new ArrayList<>();
    // get the bytes from the msg
    byte[] byteMsg = msg.getBytes();

    // insert them into the List
    for (byte stringByte : byteMsg) {
      rawData.add(stringByte);
    }

    // add start byte to escaped data
    escapedData.add(START_BYTE);
    // escape the raw data from the msg
    escapedData.addAll(escapeRawData(rawData));
    // add size from escaped Data without the START_BYTE
    escapedData.add(1, ((byte) (escapedData.size() - 1)));
    // add end byte
    escapedData.add(END_BYTE);

    return arrayListToByteArray(escapedData);
  }

  /**
   * If the rawData contains bytes which equals the START/END/ESCAPE_BYTE, it will cause problems.
   * Therefore this method escapes the bytes, which matches the START/END/ESCAPE_BYTE by adding an ESCAPE_BYTE before the byte itself.
   * @param rawData the list with bytes from the msg.
   * @return a list of escaped data.
   */
  private ArrayList<Byte> escapeRawData(ArrayList<Byte> rawData) {
    ArrayList<Byte> escapedData = new ArrayList<>();

    for (byte rawByte : rawData) {
      // check if they are equal
      if (rawByte == START_BYTE || rawByte == END_BYTE || rawByte == ESCAPE_BYTE) {
        // when they are add an escape byte
        escapedData.add(ESCAPE_BYTE);
      }
      // add the raw byte
      escapedData.add(rawByte);
    }

    return escapedData;
  }

  // convert the arrayList to byteArray since we can only send byte arrays
  private byte[] arrayListToByteArray(ArrayList<Byte> arrayList) {
    byte[] bytes = new byte[arrayList.size()];

    for (int i = 0; i < arrayList.size(); i++) {
      bytes[i] = arrayList.get(i);
    }

    return bytes;
  }
}
