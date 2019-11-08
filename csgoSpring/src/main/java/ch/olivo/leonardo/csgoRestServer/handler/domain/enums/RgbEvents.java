package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public enum RgbEvents {


  DAMAGE(1, new ColorEvent(new Color(195, 16, 26), null, ColorEventType.BLINKING)),
  EXPLOADED(2, new ColorEvent(new Color(195, 72, 8), new Color(195, 57, 28), ColorEventType.THREELEDSTRING)),
  DEFUSED(2, new ColorEvent(new Color(50, 85, 195), new Color(33, 140, 195), ColorEventType.BLINKING)),
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


  // TODO REFACTOR PLEASE IT LOOKS DISGUSTING
  public RgbEvents defineEvent(CsgoEvent event) {
    List<RgbEvents> eventsList = new ArrayList<>();

    if (event.getPlayer().getPlayerState().isBurning()) {
      eventsList.add(RgbEvents.BURNING);
    }

    if (event.getPlayer().getPlayerState().isFlashed()) {
      eventsList.add(RgbEvents.FLASHED);
    }

    if (event.getPlayer().getPlayerState().isSmoked()) {
      eventsList.add(RgbEvents.SMOKED);
    }

    for (Weapon weapon : event.getPlayer().getWeapons()) {
      if (weapon.getWeaponState() == WeaponState.ACTIVE) {
        switch (weapon.getGrenadeType()) {
          case HE:
            eventsList.add(RgbEvents.HE);
            break;
          case DECOY:
            eventsList.add(RgbEvents.DECOY);
            break;
          case SMOKE:
            eventsList.add(RgbEvents.SMOKE);
            break;
          case MOLOTOV:
            eventsList.add(RgbEvents.MOLOTOV);
            break;
          case FLASHBANG:
            eventsList.add(RgbEvents.FLASHBANG);
        }
      }
    }

    if (event.getRound().getPhase() == RoundPhase.FREEZETIME) {
      eventsList.add(RgbEvents.FREEZTIME);

    } else if (event.getRound().getPhase() == RoundPhase.OVER) {

      switch (event.getRound().getWin_team()) {

        case T:
          if (event.getPlayer().getTeam() == Team.T) {
            eventsList.add(RgbEvents.ROUNDOVERWIN);
          } else {
            eventsList.add(RgbEvents.ROUNDOVERLOST);
          }
          break;

        case CT:
          if (event.getPlayer().getTeam() == Team.CT) {
            eventsList.add(RgbEvents.ROUNDOVERWIN);
          } else {
            eventsList.add(RgbEvents.ROUNDOVERLOST);
          }
          break;

      }

      if (event.getRound().getBomb() != null) {
        switch (event.getRound().getBomb()) {
          case DEFUSED:
            eventsList.add(RgbEvents.DEFUSED);
            break;
          case EXPLODED:
            eventsList.add(RgbEvents.EXPLOADED);
            break;
        }
      }

    } else if (event.getRound().getPhase() == RoundPhase.LIVE)
      if (event.getRound().getBomb() != null && event.getRound().getBomb() == BombState.PLANTED) {
        eventsList.add(RgbEvents.PLANTED);
      }

    switch (event.getPlayer().getTeam()) {
      case CT:
        eventsList.add(RgbEvents.CT);
        break;
      case T:
        eventsList.add(RgbEvents.T);
        break;
    }
    return null;
  }
}