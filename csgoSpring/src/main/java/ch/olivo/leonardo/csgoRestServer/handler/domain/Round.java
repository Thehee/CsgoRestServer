package ch.olivo.leonardo.csgoRestServer.handler.domain;

import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.BombState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RoundPhase;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Round {
  private RoundPhase phase;
  private BombState bomb;
  private Team win_team;
}
