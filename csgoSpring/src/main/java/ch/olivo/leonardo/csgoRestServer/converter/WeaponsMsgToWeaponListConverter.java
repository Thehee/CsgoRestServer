package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.WeaponMsg;
import ch.olivo.leonardo.csgoRestServer.controller.msg.WeaponsMsg;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeaponsMsgToWeaponListConverter {

  @Autowired
  private WeaponMsgToWeaponConverter weaponConverter;

  public List<Weapon> convert(WeaponsMsg weaponsMsg) {
    if (weaponsMsg == null) {
      return null;
    }
    List<Weapon> weapons = new ArrayList<>();

    // is kinda ugly. still a little ugly but better.
    for (int i = 0; i < 9; i++) {
      try {
        String methodName = "getWeapon_" + i;
        Method method = weaponsMsg.getClass().getMethod(methodName);

        WeaponMsg weapon = (WeaponMsg) method.invoke(weaponsMsg);
        if (weapon != null) {
          weapons.add(weaponConverter.convert(weapon, i));
        }
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }

    return weapons;
  }
}
