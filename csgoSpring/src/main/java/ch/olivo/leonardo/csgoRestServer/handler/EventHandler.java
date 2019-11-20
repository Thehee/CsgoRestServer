package ch.olivo.leonardo.csgoRestServer.handler;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import ch.olivo.leonardo.csgoRestServer.converter.CsgoEventRequestToCsgoEventConverter;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.service.RgbEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

  @Autowired
  private CsgoEventRequestToCsgoEventConverter csgoEventConverter;

  @Autowired
  private RgbEventService service;

  public void handleEvent(CsgoEventRequest eventRequest) {
    CsgoEvent csgoEvent = csgoEventConverter.convert(eventRequest);
    System.out.println(service.defineEvent(csgoEvent));
  }
}
