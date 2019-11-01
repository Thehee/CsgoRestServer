package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class PlayerState {
  private int health;
  private int armor;
  private boolean helmet;
  private boolean flashed;
  private boolean smoked;
  private boolean burning;
  private int money;
  private int round_kills;
  private int round_killhs;
  private int equip_value;
}
