package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum GrenadeType {
  FLASHBANG {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.FLASHBANG;
    }
  },
  MOLOTOV {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.MOLOTOV;
    }
  },
  HE {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.HE;
    }
  },
  DECOY {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.DECOY;
    }
  },
  SMOKE {
    @Override
    public RgbEvents asRgbEvent() {
      return RgbEvents.SMOKE;
    }
  };

  // default
  public RgbEvents asRgbEvent() {
    return null;
  }

  public static GrenadeType byName(String name) {
    String modifiedName = name.replace("weapon_", "").replace("grenade", "");

    if (modifiedName.equals("inc")) {
      return GrenadeType.MOLOTOV;
    } else {
      return Arrays.stream(GrenadeType.values()).filter(value -> value.name().toLowerCase().equals(modifiedName)).findFirst()
          .orElse(null);
    }
  }
}
