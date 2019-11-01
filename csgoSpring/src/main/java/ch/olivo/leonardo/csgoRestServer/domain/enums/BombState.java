package ch.olivo.leonardo.csgoRestServer.domain.enums;

import java.util.Arrays;

public enum BombState {
  EXPLODED,
  PLANTED,
  DEFUSED;

  public static BombState byString(String state) {
    return Arrays.stream(BombState.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
