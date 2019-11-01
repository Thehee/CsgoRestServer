package ch.olivo.leonardo.csgoRestServer.domain.enums;

import java.util.Arrays;

public enum WinTeam {
  T,
  CT;

  public static WinTeam byString(String state) {
    return Arrays.stream(WinTeam.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
