package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsgoEventRequestToCsgoEventConverter {

  @Autowired
  private PlayerMsgToPlayerConverter playerConverter;

  @Autowired
  private RoundMsgToRoundConverter roundConverter;

  @Autowired
  private PreviouslyMsgToPreviouslyConverter previouslyConverter;

  public CsgoEvent convert(CsgoEventRequest csgoEventRequest) {
    return CsgoEvent.builder()
        .player(playerConverter.convert(csgoEventRequest.getPlayer()))
        .round(roundConverter.convert(csgoEventRequest.getRound()))
        .previously(previouslyConverter.converter(csgoEventRequest.getPreviously()))
        .build();
  }
}
