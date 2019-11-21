package ch.olivo.leonardo.csgoRestServer.controller.msg;

import lombok.Getter;

@Getter
public class CsgoEventRequest {
  private ProviderMsg provider;
  private MapMsg map;
  private RoundMsg round;
  private PlayerMsg player;
  private Added added;
  private AuthMsg auth;
  private PreviouslyMsg previously;
}
