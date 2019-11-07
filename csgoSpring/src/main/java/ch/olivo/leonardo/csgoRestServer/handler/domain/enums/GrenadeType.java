package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum GrenadeType {
  FLASHBANG,
  MOLOTOV,
  HE,
  DECOY,
  SMOKE;

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
