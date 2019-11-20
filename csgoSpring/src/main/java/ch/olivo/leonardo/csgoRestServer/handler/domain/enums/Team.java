package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum Team {
  T {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.T;
    }
  },
  CT {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.CT;
    }
  },;

  // default
  public RgbEvent asRgbEvent() {
    return null;
  }

  public static Team byString(String state) {
    return Arrays.stream(Team.values())
        .filter(value -> value.name().equals(state)).findFirst().orElse(null);
  }
}
