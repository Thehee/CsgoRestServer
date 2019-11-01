package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class AddedPlayer {
  private Boolean clan;
  // can be a boolean or an Object (Added_weapons)
  private Object weapons;
  private Boolean observer_slot;
  private Boolean team;
  private Boolean state;
  private Boolean match_stats;
}
