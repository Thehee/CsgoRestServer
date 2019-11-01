package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class Player {
  private String steamid;
  private String clan;
  private String name;
  private String team;
  private Integer observer_slot;
  private PlayerState state;
  private Weapons weapons;
  private MatchStats match_stats;
}
