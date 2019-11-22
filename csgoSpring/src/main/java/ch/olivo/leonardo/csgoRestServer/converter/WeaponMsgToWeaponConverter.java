package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.WeaponMsg;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.GrenadeType;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponType;
import org.springframework.stereotype.Service;

@Service
public class WeaponMsgToWeaponConverter {

  public Weapon convert(WeaponMsg weapon, int index) {

    if (weapon == null) {
      return null;
    }
    return Weapon.builder()
        .index(index)
        .name(weapon.getName())
        .ammo_clip(weapon.getAmmo_clip())
        .weaponState(WeaponState.byString(weapon.getState()))
        .type(WeaponType.byString(weapon.getType(), weapon.getName()))
        .grenadeType(GrenadeType.byName(weapon.getName()))
        .build();

  }
}
