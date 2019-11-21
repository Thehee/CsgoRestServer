package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class PreviouslyMsg {
  private PlayerMsg player;
  private RoundMsg round;
}
