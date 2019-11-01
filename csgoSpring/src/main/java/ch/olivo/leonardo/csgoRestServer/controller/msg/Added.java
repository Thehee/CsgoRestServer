package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class Added {
  // can be a boolean or an object (Added_round)
  private Object round;
  private AddedPlayer player;
  private AddedMap map;
}
