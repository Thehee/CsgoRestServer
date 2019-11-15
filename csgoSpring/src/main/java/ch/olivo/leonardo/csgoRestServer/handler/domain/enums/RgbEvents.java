package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Round;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * ugly enum
 */
@AllArgsConstructor
@Getter
public enum RgbEvents {


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

  // ----------------------------- rgb event collector
  // TODO REFACTOR PLEASE IT LOOKS DISGUSTING. Update 1: looks a little better
  // TODO add damage and shooting when previously is implemented.

  /**
   * returns the event that should be ran
   * @param event
   * @return RgbEvent
   */
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

    eventsList.add(activeNade(event.getPlayer().getWeapons()));

    eventsList.addAll(roundPhases(event.getRound(), event.getPlayer().getTeam()));

    eventsList.add(team(event.getPlayer().getTeam()));

    eventsList.removeAll(Collections.singleton(null));

    if (eventsList.size() != 0) {
      return sortListByPriority(eventsList).get(0);
    }

    return null;
  }

  // ----------------------------- rgb event definer classes

  // returns the active nade if there is any
  private RgbEvents activeNade(List<Weapon> weapons) {

    for (Weapon weapon : weapons) {
      if (weapon.getWeaponState().isActive()) {
        weapon.getGrenadeType().asRgbEvent();
      }
    }

    return null;
  }

  // returns a list of events depending on the round phase
  private List<RgbEvents> roundPhases(Round round, Team team) {
    List<RgbEvents> events = new ArrayList<>();
    if (round.getPhase() == RoundPhase.FREEZETIME) {
      events.add(RgbEvents.FREEZTIME);

    } else if (round.getPhase() == RoundPhase.OVER) {

      events.add(bombEvent(round.getBomb()));
      if (round.getWin_team() != null) {
        events.add(winnerTeam(round.getWin_team(), team));
      }
    } else if (round.getPhase() == RoundPhase.LIVE) {
      if (round.getBomb() != null) {
        events.add(round.getBomb().asRgbEvent());
      }
    }
    return events;
  }

  // returns the bomb state
  private RgbEvents bombEvent(BombState bombState) {
    return bombState.asRgbEvent();
  }

  // defines the team of the player and returns the RGB event
  private RgbEvents team(Team team) {

    return team.asRgbEvent();

  }

  // defines the winner team and returns the rgb event
  private RgbEvents winnerTeam(Team winTeam, Team team) {

    if (winTeam == team) {
      return RgbEvents.ROUNDOVERWIN;
    } else {
      return RgbEvents.ROUNDOVERLOST;
    }

  }

  // ----------------------------- sorting

  // TODO check if there is a better way to sort this
  private List<RgbEvents> sortListByPriority(List<RgbEvents> srcList) {
    List<RgbEvents> sortedList = new ArrayList<>();
    int highestPriority = getHighestPriority(srcList);

    for (int i = 0; i <= highestPriority; i++) {
      for (RgbEvents event : srcList) {
        if (event.getPriority() == i) {
          sortedList.add(event);
        }
      }
    }

    return sortedList;
  }

  private int getHighestPriority(List<RgbEvents> list) {
    int highestPriority = 0;

    for (RgbEvents event : list) {
      if (highestPriority < event.getPriority()) {
        highestPriority = event.getPriority();
      }
    }

    return highestPriority;
  }
}