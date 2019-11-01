package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class State {
  private int health;
  private int armor;
  private boolean helmet;
  private int flashed;
  private int smoked;
  private int burning;
  private int money;
  private int round_kills;
  private int round_killhs;
  private int equip_value;
}
