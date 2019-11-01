package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class Round {
  private String phase;
  private String bomb;
  private String win_team;
}
