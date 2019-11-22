package ch.olivo.leonardo.csgoRestServer.handler;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import ch.olivo.leonardo.csgoRestServer.converter.CsgoEventRequestToCsgoEventConverter;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RgbEvent;
import ch.olivo.leonardo.csgoRestServer.service.PortService;
import ch.olivo.leonardo.csgoRestServer.service.RgbEventService;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.PortUnreachableException;

@Component
public class EventHandler {

  @Autowired
  private CsgoEventRequestToCsgoEventConverter csgoEventConverter;

  @Autowired
  private RgbEventService rgbService;

  @Autowired
  private PortService portService;

  private SerialPort comPort;

  public void handleEvent(CsgoEventRequest eventRequest) throws PortUnreachableException {
    CsgoEvent csgoEvent = csgoEventConverter.convert(eventRequest);
    RgbEvent rgbEvent = rgbService.defineEvent(csgoEvent);
    System.out.println(rgbEvent);

    comPort = portService.openPort(comPort);

    byte[] buffer = rgbService.hexStringToByteArray(String.valueOf(rgbEvent.getColorEvent().getColor().hashCode()));

    try {
      comPort.writeBytes("hello".getBytes());
    } catch (SerialPortException e) {
      e.printStackTrace();
    }
  }

}
