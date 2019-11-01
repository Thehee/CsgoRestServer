package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.domain.Player;
import ch.olivo.leonardo.csgoRestServer.domain.Weapon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerMsgToPlayerConverter {

  private WeaponsMsgToWeaponListConverter weaponListConverter;

  public Player convert(ch.olivo.leonardo.csgoRestServer.controller.msg.Player player) {
    List<Weapon> weapons = weaponListConverter.convert(player.getWeapons());
    return Player.builder()
        .team(player.getTeam())
        .weapons(weapons)
        .build();
  }
}
