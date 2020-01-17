package ch.olivo.leonardo.csgoRestServer.handler;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import org.springframework.stereotype.Component;

@Component
public class GpuEventHandler implements EventHandler {

  @Override
  public void handleEvent(CsgoEventRequest eventRequest) {
    System.out.println("hello");
  }
}
