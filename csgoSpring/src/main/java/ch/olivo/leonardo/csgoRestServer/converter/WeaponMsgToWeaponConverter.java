package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.event_handler.domain.Weapon;
import ch.olivo.leonardo.csgoRestServer.event_handler.domain.enums.WeaponState;
import ch.olivo.leonardo.csgoRestServer.event_handler.domain.enums.WeaponType;
import org.springframework.stereotype.Component;

@Component
public class WeaponMsgToWeaponConverter {

  public Weapon convert(ch.olivo.leonardo.csgoRestServer.controller.msg.Weapon weapon) {
    if (!weapon.getName().isEmpty()) {
      return Weapon.builder()
          .name(weapon.getName())
          .ammo_clip(weapon.getAmmo_clip())
          .weaponState(WeaponState.byString(weapon.getName()))
          .type(WeaponType.byString(weapon.getType(), weapon.getName()))
          .build();
    }
    return null;
  }
}
