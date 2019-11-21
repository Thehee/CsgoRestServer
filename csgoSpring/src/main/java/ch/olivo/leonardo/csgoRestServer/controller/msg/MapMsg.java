package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class MapMsg {
  private String mode;
  private String name;
  private String phase;
  private int round;
  private TeamMsg team_ct;
  private TeamMsg team_t;
  private int num_matches_to_win_series;
  private int current_spectators;
  private int souvenirs_total;
}
