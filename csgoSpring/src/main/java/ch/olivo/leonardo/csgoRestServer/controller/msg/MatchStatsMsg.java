package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class MatchStatsMsg {
  private int kills;
  private int assists;
  private int deaths;
  private int mvps;
  private int score;
}
