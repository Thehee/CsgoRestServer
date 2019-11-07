package ch.olivo.leonardo.csgoRestServer.handler.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerState {
  private int health;
  private boolean flashed;
  private boolean smoked;
  private boolean burning;
  private int round_kills;
  private int round_killhs;
}
