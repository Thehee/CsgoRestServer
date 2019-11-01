package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class AddedRound {
  private Boolean win_team;
  private Boolean phase;
  private Boolean bomb;
}
