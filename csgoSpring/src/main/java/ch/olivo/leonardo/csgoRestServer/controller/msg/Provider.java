package ch.olivo.leonardo.csgoRestServer.controller.msg;


import lombok.Getter;

import java.sql.Time;


@Getter
public class Provider {
  private String name;
  private int appId;
  private String steamId;
  private Time timestamp;
}
