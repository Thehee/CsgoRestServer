package ch.olivo.leonardo.csgoRestServer.domain;

import ch.olivo.leonardo.csgoRestServer.domain.enums.BombState;
import ch.olivo.leonardo.csgoRestServer.domain.enums.RoundPhase;
import ch.olivo.leonardo.csgoRestServer.domain.enums.WinTeam;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Round {
  private RoundPhase phase;
  private BombState bomb;
  private WinTeam win_team;
}
