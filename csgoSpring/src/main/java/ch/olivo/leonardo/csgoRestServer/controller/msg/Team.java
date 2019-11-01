package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class Team {
  private int score;
  private int timeouts_remaining;
  private int matches_won_this_series;
}
