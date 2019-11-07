package ch.olivo.leonardo.csgoRestServer.domain.enums;

import java.util.Arrays;

public enum WeaponType {
  C4,
  KNIFE,
  RIFLE,
  TASER,
  PISTOL,
  GRENADE,
  SHOTGUN,
  MACHINEGUN,
  SNIPERRIFLE,
  SUBMACHINEGUN;

  public static WeaponType byString(String type, String name) {
    return Arrays.stream(WeaponType.values()).filter(value -> value.name().toLowerCase()
        .equals(type.replaceAll("\\s+", ""))).findFirst()
        .orElse(Arrays.stream(WeaponType.values())
            .filter(value -> name.contains(value.name().toLowerCase())).findFirst().orElse(null));
  }
}
