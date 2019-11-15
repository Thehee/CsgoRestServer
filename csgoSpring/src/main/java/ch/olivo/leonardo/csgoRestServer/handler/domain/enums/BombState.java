package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum BombState {
  EXPLODED {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.EXPLOADED;
    }
  },
  PLANTED {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.PLANTED;
    }
  },
  DEFUSED {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.DEFUSED;
    }
  };

  // default
  public RgbEvents asRgbEvent() {
    return null;
  }

  public static BombState byString(String state) {
    return Arrays.stream(BombState.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
