package ch.olivo.leonardo.csgoRestServer.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.stereotype.Component;

@Component
public class PortService {

  public SerialPort openPort(SerialPort comPort) {
    if (comPort == null) {

      for (String portName : SerialPortList.getPortNames()) {
        System.out.println(portName);
      }

      comPort = new SerialPort("COM5");

//      comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

      try {

        if (comPort.isOpened()) {
          comPort.closePort();
        }

        comPort.openPort();

      } catch (SerialPortException e) {
        e.printStackTrace();
      }

      return comPort;

//      comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
    } else if(!comPort.isOpened()) {
      try {
        comPort.openPort();
      } catch (SerialPortException e) {
        e.printStackTrace();
      }
      return comPort;
    } else {
      try {
        comPort.closePort();
      } catch (SerialPortException e) {
        e.printStackTrace();
      }
      try {
        comPort.openPort();
      } catch (SerialPortException e) {
        e.printStackTrace();
      }
    }
    return comPort;
  }
}
