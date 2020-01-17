package ch.olivo.leonardo.csgoRestServer.handler;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import ch.olivo.leonardo.csgoRestServer.converter.CsgoEventRequestToCsgoEventConverter;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RgbEvent;
import ch.olivo.leonardo.csgoRestServer.service.SerialPortService;
import ch.olivo.leonardo.csgoRestServer.handler.converter.CsgoEventToRgbEventConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArduinoEventHandler implements EventHandler {

  @Autowired
  private CsgoEventRequestToCsgoEventConverter csgoEventConverter;

  @Autowired
  private CsgoEventToRgbEventConverter rgbService;

  @Autowired
  private SerialPortService serialPortService;

  @Override
  public void handleEvent(CsgoEventRequest eventRequest) {
    // convert from msg to domain
    CsgoEvent csgoEvent = csgoEventConverter.convert(eventRequest);

    // filter out the event that should be displayed
    RgbEvent rgbEvent = rgbService.defineEvent(csgoEvent);

    log.info(String.valueOf(rgbEvent));

    serialPortService.writeString(rgbEvent);
  }

}
