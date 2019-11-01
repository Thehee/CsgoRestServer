package ch.olivo.leonardo.csgoRestServer.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Player {
  private String team;
  private List<Weapon> weapons;
  private PlayerState playerState;
}
