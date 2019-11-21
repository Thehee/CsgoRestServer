package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Getter
public enum WeaponState {
  HOLSTERED,
  ACTIVE,
  RELOADING;

  public static WeaponState byString(String state) {
    if (StringUtils.isEmpty(state)) {
      return null;
    }
    return Arrays.stream(WeaponState.values())
        .filter(value -> value.name().toLowerCase().equals(state.toLowerCase())).findFirst().orElse(null);
  }

  public boolean isActive() {
    return this == WeaponState.ACTIVE;
  }

}
