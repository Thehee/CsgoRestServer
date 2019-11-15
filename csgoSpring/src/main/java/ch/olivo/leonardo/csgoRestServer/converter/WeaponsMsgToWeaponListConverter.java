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

    // the weapons are always from 0 (first) to 8 (last) so if the first is null all the others are null as well
    // is kinda ugly
    if (weaponsMsg.getWeapon_0() != null) {
      weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_0()));

      if (weaponsMsg.getWeapon_1() != null) {
        weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_1()));

        if (weaponsMsg.getWeapon_2() != null) {

          weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_2()));

          if (weaponsMsg.getWeapon_3() != null) {

            weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_3()));

            if (weaponsMsg.getWeapon_4() != null) {

              weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_4()));

              if (weaponsMsg.getWeapon_5() != null) {

                weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_5()));

                if (weaponsMsg.getWeapon_6() != null) {
                  weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_6()));

                  if (weaponsMsg.getWeapon_7() != null) {
                    weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_7()));

                    if (weaponsMsg.getWeapon_8() != null) {

                      weapons.add(weaponConverter.convert(weaponsMsg.getWeapon_8()));
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return weapons;
  }
}
