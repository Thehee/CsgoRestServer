package ch.olivo.leonardo.csgoRestServer.handler;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import ch.olivo.leonardo.csgoRestServer.converter.CsgoEventRequestToCsgoEventConverter;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RgbEvent;
import ch.olivo.leonardo.csgoRestServer.service.PortService;
import ch.olivo.leonardo.csgoRestServer.service.RgbEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {

  @Autowired
  private CsgoEventRequestToCsgoEventConverter csgoEventConverter;

  @Autowired
  private RgbEventService rgbService;

  @Autowired
  private PortService portService;

  public void handleEvent(CsgoEventRequest eventRequest) {
    // convert from msg to domain
    CsgoEvent csgoEvent = csgoEventConverter.convert(eventRequest);

    // filter out the event that should be displayed
    RgbEvent rgbEvent = rgbService.defineEvent(csgoEvent);

    log.info(String.valueOf(rgbEvent));

    portService.writeString(rgbEvent);
  }

}
