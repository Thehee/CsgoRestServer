package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.Weapons;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeaponsMsgToWeaponListConverter {

  @Autowired
  private WeaponMsgToWeaponConverter weaponConverter;

  public List<Weapon> convert(Weapons weaponsMsg) {
    List<Weapon> weapons = new ArrayList<>();
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_1()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_2()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_3()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_4()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_5()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_6()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_7()));
    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_8()));
    return weapons;
  }
}
