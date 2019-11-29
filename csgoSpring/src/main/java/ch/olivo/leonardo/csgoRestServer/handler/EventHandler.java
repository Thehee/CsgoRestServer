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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;

@Component
public class EventHandler {

  @Autowired
  private CsgoEventRequestToCsgoEventConverter csgoEventConverter;

  @Autowired
  private RgbEventService rgbService;

  @Autowired
  private PortService portService;

  private SerialPort comPort;

  public void handleEvent(CsgoEventRequest eventRequest) {
    // convert from msg to domain
    CsgoEvent csgoEvent = csgoEventConverter.convert(eventRequest);

    // filter out the event that should be displayed
    RgbEvent rgbEvent = rgbService.defineEvent(csgoEvent);
    System.out.println(rgbEvent);

    // write "hello" (just testing it)
//    if (portService.writeString("Hello", comPort)) {
//      System.out.println("Success");
//    } else {
//      System.out.println("Fail");
//    }

    byte[] response = portService.writeString("hello", comPort);
    System.out.println(new String(response, StandardCharsets.UTF_8));
  }

  @PostConstruct
  public void openPort() {
    // instance the port
    comPort = portService.createPort(comPort);

    // open the Port
    comPort = portService.openPort(comPort);
  }

  @PreDestroy
  public void closePort() {
    try {
      comPort.closePort();
    } catch (SerialPortException e) {
      e.printStackTrace();
    }
  }
}
