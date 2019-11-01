package ch.olivo.leonardo.csgoRestServer.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CsgoEvent {
  private Player player;
  private Round round;
}
