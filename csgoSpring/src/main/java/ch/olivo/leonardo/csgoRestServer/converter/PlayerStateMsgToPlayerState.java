package ch.olivo.leonardo.csgoRestServer.converter;

import ch.olivo.leonardo.csgoRestServer.domain.PlayerState;
import org.springframework.stereotype.Component;

@Component
public class PlayerStateMsgToPlayerState {

  public PlayerState convert(ch.olivo.leonardo.csgoRestServer.controller.msg.PlayerState playerState) {
    return PlayerState.builder()
        .burning(playerState.isBurning())
        .flashed(playerState.isFlashed())
        .smoked(playerState.isSmoked())
        .health(playerState.getHealth())
        .round_kills(playerState.getRound_kills())
        .round_killhs(playerState.getRound_killhs())
        .build();
  }
}
