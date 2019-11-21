package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class WeaponMsg {
  private String name;
  private String paintkit;
  private String type;
  private Integer ammo_clip;
  private Integer ammo_clip_max;
  private Integer ammo_reserve;
  private String state;
}
