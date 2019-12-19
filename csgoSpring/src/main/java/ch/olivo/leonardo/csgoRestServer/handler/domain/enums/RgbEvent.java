package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import ch.olivo.leonardo.csgoRestServer.service.GetColorConfigYml;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * ugly enum
 */
@AllArgsConstructor
@Getter
public enum RgbEvent {

  BURNING(1, GetColorConfigYml.getColorEvent("burning")),
  DAMAGE(2, GetColorConfigYml.getColorEvent("damage")),
  EXPLODED(3,  GetColorConfigYml.getColorEvent("exploded")),
  DEFUSED(3, GetColorConfigYml.getColorEvent("defused")),
  ROUNDOVERWIN(4,  GetColorConfigYml.getColorEvent("roundoverwin")),
  ROUNDOVERLOST(4, GetColorConfigYml.getColorEvent("roundoverlost")),
  FLASHED(5, GetColorConfigYml.getColorEvent("flashed")),
  SMOKED(6, GetColorConfigYml.getColorEvent("smoked")),
  DECOY(7, GetColorConfigYml.getColorEvent("decoy")),
  SMOKE(7, GetColorConfigYml.getColorEvent("smoke")),
  MOLOTOV(7, GetColorConfigYml.getColorEvent("molotov")),
  HE(7, GetColorConfigYml.getColorEvent("he")),
  FLASHBANG(7, GetColorConfigYml.getColorEvent("flashbang")),
  LONGLINE(8, GetColorConfigYml.getColorEvent("longline")),
  MEDIUMLINE(8, GetColorConfigYml.getColorEvent("mediumline")),
  SHORTLINE(8, GetColorConfigYml.getColorEvent("shortline")),
  TASERSHOT(8, GetColorConfigYml.getColorEvent("tasershot")),
  FREEZTIME(9,GetColorConfigYml.getColorEvent("freeztime")),
  PLANTED(9, GetColorConfigYml.getColorEvent("planted")),
  T(10, GetColorConfigYml.getColorEvent("t")),
  CT(10, GetColorConfigYml.getColorEvent("ct"));

  private final int priority;
  private final ColorEvent colorEvent;

}