package ch.olivo.leonardo.csgoRestServer.service;

import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RgbEvent;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.*;
import java.util.ArrayList;

@Component
public class PortService {

  private SerialPort comPort;
  private final byte START_BYTE = 0x7E;
  private final byte END_BYTE = 0x25;
  private final byte ESCAPE_BYTE = 0x3F;

  @PostConstruct
  public void openPort() {
    // instance the port
    createPort();

    // open the Port
    openThePort();
  }

  @PreDestroy
  public void closePort() {
    try {
      comPort.closePort();
    } catch (SerialPortException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method makes a new instance of the port COM5.
   * It checks if there is already an instance of a port before it creates a new one.
   */
  private void createPort() {
    if (comPort != null) {
      return;
    }
    // print out all port names. I have only one (COM5).
    for (String portName: SerialPortList.getPortNames()) {
      System.out.println(portName);
    }

    // new SerialPort which is COM5 in my case
    comPort = new SerialPort("COM5");
  }

  /**
   * This method tries to open a port.
   */
  private void openThePort() {

    if (!comPort.isOpened()) {
      try {
        comPort.openPort();
        comPort.setParams(9600, 8, 1, 0);
      } catch (SerialPortException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * This method writes a string, through a port.
   * @param rgbEvent event to write.
   */
  public void writeString(RgbEvent rgbEvent) {

    // create a byte array with the msg; escaped test msg as byte array z.b.(126 4 3 -61 16 26 37 37)
    byte[] escapedMsg = createMsg(rgbEvent);

    try {
      // try to send msg over the port.
      comPort.writeBytes(escapedMsg);
//      response
//      Thread.sleep(100);
//      for (int number: comPort.readIntArray()) {
//        System.out.println(number);
//      }

//      System.out.println(Arrays.toString(comPort.readIntArray()));
      // return comPort.readBytes(escapedMsg.length);

    } catch (SerialPortException /*| InterruptedException*/ e) {
      e.printStackTrace();
    }
//    return null;
  }

  /**
   * this method creates a byte array which fulfils a certain protocol .
   * @param rgbEvent the event which should be converted to the byte array.
   * @return byte array which fulfils the protocol and includes the msg.
   */
  private byte[] createMsg(RgbEvent rgbEvent) {
    ArrayList<Byte> rawData = new ArrayList<>();
    ArrayList<Byte> escapedData = new ArrayList<>();

    int commandNumber = rgbEvent.getColorEvent().getColorEventType().asCommandNumber();
    Color color1 = rgbEvent.getColorEvent().getColor();
    Color color2 = rgbEvent.getColorEvent().getSecondColor();


    rawData.add((byte) commandNumber);
    rawData.addAll(rgbToByteList(color1));
    if (color2 != null) {
      rawData.addAll(rgbToByteList(color2));
    }
    // add start byte to escaped data
    escapedData.add(START_BYTE);
    // escape the raw data from the msg
    escapedData.addAll(escapeRawData(rawData));
    // add size from escaped Data without the START_BYTE
    escapedData.add(1, ((byte) (escapedData.size() - 1)));
    // add end byte
    escapedData.add(END_BYTE);
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

    for (byte rawByte: rawData) {
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

  private ArrayList<Byte> rgbToByteList(Color color) {
    ArrayList<Byte> rgbByte = new ArrayList<>();

    rgbByte.add((byte) color.getRed());
    rgbByte.add((byte) color.getGreen());
    rgbByte.add((byte) color.getBlue());

    return rgbByte;
  }

}
