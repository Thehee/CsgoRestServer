package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum GrenadeType {
  FLASHBANG {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.FLASHBANG;
    }
  },
  MOLOTOV {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.MOLOTOV;
    }
  },
  HE {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.HE;
    }
  },
  DECOY {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.DECOY;
    }
  },
  SMOKE {
    @Override
    public RgbEvent asRgbEvent() {
      return RgbEvent.SMOKE;
    }
  };

  // default
  public RgbEvent asRgbEvent() {
    return null;
  }

  /**
   * returns the grenade type by the name of the weapon
   * @param name
   * @return GrenadeType
   */
  public static GrenadeType byName(String name) {
    String modifiedName = name.replace("weapon_", "").replace("grenade", "");

    if (modifiedName.equals("inc")) {
      return GrenadeType.MOLOTOV;
    } else {
      return Arrays.stream(GrenadeType.values()).filter(value -> value.name().toLowerCase().equals(modifiedName))
          .findFirst().orElse(null);
    }
  }
}
