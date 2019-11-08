package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.handler.domain.Round;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.BombState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RoundPhase;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.Team;
import org.springframework.stereotype.Component;

@Component
public class RoundMsgToRoundConverter {

  public Round convert(ch.olivo.leonardo.csgoRestServer.controller.msg.Round round) {
    return Round.builder()
        .phase(RoundPhase.byString(round.getPhase()))
        .bomb(BombState.byString(round.getBomb()))
        .win_team(Team.byString(round.getWin_team()))
        .build();
  }
}
