package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class CsgoEventRequest {
  private Provider provider;
  private Map map;
  private Round round;
  private Player player;
  private Added added;
  private Auth auth;
  private Previously previously;
}
