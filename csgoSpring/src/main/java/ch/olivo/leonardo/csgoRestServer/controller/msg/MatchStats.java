package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class MatchStats {
  private int kills;
  private int assists;
  private int deaths;
  private int mvps;
  private int score;
}
