package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum Team {
  T,
  CT;

  public static Team byString(String state) {
    return Arrays.stream(Team.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
