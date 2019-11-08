package ch.olivo.leonardo.csgoRestServer.handler.domain;

import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.Team;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Player {
  private Team team;
  private List<Weapon> weapons;
  private PlayerState playerState;
}
