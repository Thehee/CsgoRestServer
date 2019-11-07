package ch.olivo.leonardo.csgoRestServer.domain;

import ch.olivo.leonardo.csgoRestServer.domain.enums.WeaponState;
import ch.olivo.leonardo.csgoRestServer.domain.enums.WeaponType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Weapon {
  private String name;
  private WeaponType type;
  private Integer ammo_clip;
  private WeaponState weaponState;
}
