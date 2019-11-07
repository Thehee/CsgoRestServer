package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.GrenadeType;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class WeaponMsgToWeaponConverter {

  public Weapon convert(ch.olivo.leonardo.csgoRestServer.controller.msg.Weapon weapon) {

    if (weapon != null && !StringUtils.isEmpty(weapon.getName())) {
      return Weapon.builder()
          .name(weapon.getName())
          .ammo_clip(weapon.getAmmo_clip())
          .weaponState(WeaponState.byString(weapon.getState()))
          .type(WeaponType.byString(weapon.getType(), weapon.getName()))
          .grenadeType(GrenadeType.byName(weapon.getName()))
          .build();
    }
    return null;
  }
}
