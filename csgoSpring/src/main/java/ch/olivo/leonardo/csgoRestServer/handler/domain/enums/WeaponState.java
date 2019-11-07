package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WeaponState {
  HOLSTERED,
  ACTIVE,
  RELOADING;

  public static WeaponState byString(String state) {
    return Arrays.stream(WeaponState.values())
        .filter(value -> value.name().toLowerCase().equals(state.toLowerCase())).findFirst().orElse(null);
  }
}
