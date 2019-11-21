package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class RoundMsg {
  private String phase;
  private String bomb;
  private String win_team;
}
