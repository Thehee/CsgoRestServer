package ch.olivo.leonardo.csgoRestServer.event_handler.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Player {
  private String team;
  private List<Weapon> weapons;
  private PlayerState playerState;
}
