package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.PreviouslyMsg;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Previously;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreviouslyMsgToPreviouslyConverter {

  @Autowired
  private PlayerMsgToPlayerConverter playerConverter;

  public Previously converter(PreviouslyMsg previouslyMsg) {
    if (previouslyMsg == null) {
      return null;
    }
    return Previously.builder()
        .player(playerConverter.convert(previouslyMsg.getPlayer()))
        .build();
  }
}
