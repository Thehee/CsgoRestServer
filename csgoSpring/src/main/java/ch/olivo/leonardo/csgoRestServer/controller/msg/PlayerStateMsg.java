package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class PlayerStateMsg {
  private int health;
  private int armor;
  private boolean helmet;
  private boolean flashed;
  private boolean smoked;
  private boolean burning;
  private boolean defusekit;
  private int money;
  private int round_kills;
  private int round_killhs;
  private int equip_value;
}
