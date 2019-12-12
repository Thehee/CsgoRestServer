package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum BombState {
  EXPLODED {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.EXPLODED;
    }
  },
  PLANTED {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.PLANTED;
    }
  },
  DEFUSED {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.DEFUSED;
    }
  };

  // default
  public RgbEvent asRgbEvent() {
    return null;
  }

  public static BombState byString(String state) {
    return Arrays.stream(BombState.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
