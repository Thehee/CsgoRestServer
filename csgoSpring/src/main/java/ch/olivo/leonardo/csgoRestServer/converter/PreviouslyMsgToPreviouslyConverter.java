package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.handler.domain.Previously;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreviouslyMsgToPreviouslyConverter {

  @Autowired
  private PlayerMsgToPlayerConverter playerConverter;

  public Previously converter(ch.olivo.leonardo.csgoRestServer.controller.msg.Previously previously) {
    return Previously.builder()
        .player(playerConverter.convert(previously.getPlayer()))
        .build();
  }
}
