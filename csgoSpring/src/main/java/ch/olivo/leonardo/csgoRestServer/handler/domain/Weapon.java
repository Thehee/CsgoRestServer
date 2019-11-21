package ch.olivo.leonardo.csgoRestServer.handler.domain;

import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.GrenadeType;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Weapon {
  private int index;
  private String name;
  private WeaponType type;
  private GrenadeType grenadeType;
  private Integer ammo_clip;
  private WeaponState weaponState;
}
