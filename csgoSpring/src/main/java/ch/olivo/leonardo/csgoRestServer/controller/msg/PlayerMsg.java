package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class PlayerMsg {
  private String steamid;
  private String clan;
  private String name;
  private String team;
  private Integer observer_slot;
  private PlayerStateMsg state;
  private WeaponsMsg weapons;
  private MatchStatsMsg match_stats;
}
