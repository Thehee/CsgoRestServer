package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class Map {
  private String mode;
  private String name;
  private String phase;
  private int round;
  private Team team_ct;
  private Team team_t;
  private int num_matches_to_win_series;
  private int current_spectators;
  private int souvenirs_total;
}
