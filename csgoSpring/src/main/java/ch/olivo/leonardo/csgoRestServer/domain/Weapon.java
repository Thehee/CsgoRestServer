package ch.olivo.leonardo.csgoRestServer.domain;

import ch.olivo.leonardo.csgoRestServer.domain.enums.WeaponState;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Weapon {
  private String name;
  private String type;
  private Integer ammo_clip;
  private WeaponState weaponState;
}
