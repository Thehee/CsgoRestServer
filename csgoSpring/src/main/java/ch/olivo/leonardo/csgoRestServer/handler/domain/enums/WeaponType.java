package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import org.springframework.util.StringUtils;

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
    String modifiedType = "";

    if (!StringUtils.isEmpty(type)) {
      modifiedType = type.replaceAll("\\s+", "");
    }

    String finalModifiedType = modifiedType.toLowerCase();
    return Arrays.stream(WeaponType.values()).filter(value -> value.name().toLowerCase()
        .equals(finalModifiedType)).findFirst().orElse(Arrays.stream(WeaponType.values())
        .filter(value -> name.contains(value.name().toLowerCase())).findFirst().orElse(null));
  }
}
