package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;


/**
 * ugly enum
 */
@AllArgsConstructor
@Getter
public enum RgbEvent {


  DAMAGE(1, new ColorEvent(new Color(195, 16, 26), null, ColorEventType.BLINKING)),
  EXPLOADED(2, new ColorEvent(new Color(195, 72, 8), new Color(195, 57, 28),
      ColorEventType.THREELEDSTRING)),
  DEFUSED(2, new ColorEvent(new Color(50, 85, 195), new Color(33, 140, 195),
      ColorEventType.BLINKING)),
  ROUNDOVERWIN(3, new ColorEvent(new Color(19, 178, 45), null, ColorEventType.BLINKING)),
  ROUNDOVERLOST(3, new ColorEvent(new Color(195, 28, 83), null, ColorEventType.DEFAULT)),
  BURNING(4, new ColorEvent(new Color(252, 82, 51), new Color(195, 28, 83),
      ColorEventType.BLINKING)),
  FLASHED(5, new ColorEvent(new Color(252, 252, 252), null, ColorEventType.FLASH)),
  SMOKED(6, new ColorEvent(new Color(128, 128, 128), null, ColorEventType.DEFAULT)),
  HE(7, new ColorEvent(new Color(139, 51, 11), null, ColorEventType.DEFAULT)),
  MOLOTOV(7, new ColorEvent(new Color(255, 89, 28), null, ColorEventType.DEFAULT)),
  DECOY(7, new ColorEvent(new Color(77, 51, 0), null, ColorEventType.DEFAULT)),
  SMOKE(7, new ColorEvent(new Color(147, 147, 147), null, ColorEventType.DEFAULT)),
  FLASHBANG(7, new ColorEvent(new Color(195, 192, 83), null, ColorEventType.DEFAULT)),
  LONGFLASH(7, new ColorEvent(new Color(195, 77, 23), new Color(195, 60, 26),
      ColorEventType.FOURLEDSTRING)),
  MEDIUMLINE(7, new ColorEvent(new Color(195, 77, 23), new Color(195, 60, 26),
      ColorEventType.THREELEDSTRING)),
  SHORTFLASH(7, new ColorEvent(new Color(195, 77, 23), new Color(195, 60, 26),
      ColorEventType.TWOLEDSTRING)),
  FREEZTIME(8, new ColorEvent(new Color(42, 165, 212), null, ColorEventType.DEFAULT)),
  PLANTED(8, new ColorEvent(new Color(208, 84, 24), null, ColorEventType.DEFAULT)),
  T(9, new ColorEvent(new Color(195, 134, 30), null, ColorEventType.DEFAULT)),
  CT(9, new ColorEvent(new Color(61, 92, 195), null, ColorEventType.DEFAULT));

  private final int priority;
  private final ColorEvent colorEvent;

}