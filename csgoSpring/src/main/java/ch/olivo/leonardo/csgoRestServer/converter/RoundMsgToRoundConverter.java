package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.domain.Round;
import ch.olivo.leonardo.csgoRestServer.domain.enums.BombState;
import ch.olivo.leonardo.csgoRestServer.domain.enums.RoundPhase;
import ch.olivo.leonardo.csgoRestServer.domain.enums.WinTeam;
import org.springframework.stereotype.Component;

@Component
public class RoundMsgToRoundConverter {
  public Round convert(ch.olivo.leonardo.csgoRestServer.controller.msg.Round round) {
    return Round.builder()
        .phase(RoundPhase.byString(round.getPhase()))
        .bomb(BombState.byString(round.getBomb()))
        .win_team(WinTeam.byString(round.getWin_team()))
        .build();
  }
}
