package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum GrenadeType {
  FLASHBANG (RgbEvent.FLASHBANG),
  MOLOTOV (RgbEvent.MOLOTOV),
  HE (RgbEvent.HE),
  DECOY (RgbEvent.DECOY),
  SMOKE (RgbEvent.SMOKE);

  // default
  private final RgbEvent rgbEvent;

  /**
   * returns the grenade type by the name of the weapon
   * @param name
   * @return GrenadeType
   */
  public static GrenadeType byName(String name) {
    if (StringUtils.isEmpty(name)) {
      return null;
    }

    String modifiedName = name.replace("weapon_", "").replace("grenade", "");

    if (modifiedName.equals("inc")) {
      return GrenadeType.MOLOTOV;
    } else {
      return Arrays.stream(GrenadeType.values()).filter(value -> value.name().toLowerCase().equals(modifiedName))
          .findFirst().orElse(null);
    }
  }
}
