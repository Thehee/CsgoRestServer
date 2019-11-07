package ch.olivo.leonardo.csgoRestServer.event_handler.domain.enums;

import java.util.Arrays;

public enum RoundPhase {
  LIVE,
  FREEZETIME,
  OVER;

  public static RoundPhase byString(String state) {
    return Arrays.stream(RoundPhase.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
