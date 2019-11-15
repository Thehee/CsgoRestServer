package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum Team {
  T {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.T;
    }
  },
  CT {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.CT;
    }
  },;

  // default
  public RgbEvents asRgbEvent() {
    return null;
  }

  public static Team byString(String state) {
    return Arrays.stream(Team.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
