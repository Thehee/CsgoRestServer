package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.controller.msg.PlayerStateMsg;
import ch.olivo.leonardo.csgoRestServer.handler.domain.PlayerState;
import org.springframework.stereotype.Component;

@Component
public class PlayerStateMsgToPlayerState {

  public PlayerState convert(PlayerStateMsg playerStateMsg) {
    if (playerStateMsg == null) {
      return null;
    }
    return PlayerState.builder()
        .burning(playerStateMsg.isBurning())
        .flashed(playerStateMsg.isFlashed())
        .smoked(playerStateMsg.isSmoked())
        .health(playerStateMsg.getHealth())
        .roundKills(playerStateMsg.getRound_kills())
        .roundKillhs(playerStateMsg.getRound_killhs())
        .build();
  }
}
