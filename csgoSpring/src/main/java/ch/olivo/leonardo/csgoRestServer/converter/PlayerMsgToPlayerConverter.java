package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.PlayerMsg;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Player;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerMsgToPlayerConverter {

  @Autowired
  private WeaponsMsgToWeaponListConverter weaponListConverter;

  @Autowired
  private PlayerStateMsgToPlayerState playerStateConverter;

  public Player convert(PlayerMsg playerMsg) {
    List<Weapon> weapons = weaponListConverter.convert(playerMsg.getWeapons());

    return Player.builder()
        .team(Team.byString(playerMsg.getTeam()))
        .weapons(weapons)
        .playerState(playerStateConverter.convert(playerMsg.getState()))
        .build();
  }
}
