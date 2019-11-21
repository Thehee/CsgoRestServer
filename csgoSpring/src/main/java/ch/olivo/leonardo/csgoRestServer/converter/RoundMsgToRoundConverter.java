package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.RoundMsg;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Round;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.BombState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RoundPhase;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.Team;
import org.springframework.stereotype.Component;

@Component
public class RoundMsgToRoundConverter {

  public Round convert(RoundMsg roundMsg) {

    if (roundMsg == null) {
      return null;
    }

    return Round.builder()
        .phase(RoundPhase.byString(roundMsg.getPhase()))
        .bomb(BombState.byString(roundMsg.getBomb()))
        .win_team(Team.byString(roundMsg.getWin_team()))
        .build();
  }
}
