package ch.olivo.leonardo.csgoRestServer.service;

import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Round;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.BombState;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.RgbEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RgbEventService {
  
  // ----------------------------- rgb event collector
  // TODO REFACTOR PLEASE IT LOOKS DISGUSTING. Update 1: looks a little better
  // TODO add damage and shooting when previously is implemented.

  /**
   * returns the event that should be ran
   * @param event
   * @return RgbEvent
   */
  public RgbEvent defineEvent(CsgoEvent event) {
    List<RgbEvent> eventsList = new ArrayList<>();

    if (event.getPlayer().getPlayerState().isBurning()) {
      eventsList.add(RgbEvent.BURNING);
    }

    if (event.getPlayer().getPlayerState().isFlashed()) {
      eventsList.add(RgbEvent.FLASHED);
    }

    if (event.getPlayer().getPlayerState().isSmoked()) {
      eventsList.add(RgbEvent.SMOKED);
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
  private RgbEvent activeNade(List<Weapon> weapons) {

    for (Weapon weapon : weapons) {
      if (weapon.getWeaponState().isActive() && weapon.getGrenadeType() != null) {
        return weapon.getGrenadeType().asRgbEvent();
      }
    }

    return null;
  }

  // returns a list of events depending on the round phase
  private List<RgbEvent> roundPhases(Round round, Team team) {
    List<RgbEvent> events = new ArrayList<>();

    switch (round.getPhase()) {

      case FREEZETIME:
        events.add(RgbEvent.FREEZTIME);
        break;

      case OVER:
        events.add(bombEvent(round.getBomb()));

        if (round.getWin_team() != null) {
          events.add(winnerTeam(round.getWin_team(), team));
        }
        break;

      case LIVE:
        if (round.getBomb() != null) {
          events.add(round.getBomb().asRgbEvent());
        }
        break;

      default:
        return null;
    }

    return events;
  }

  // returns the bomb state
  private RgbEvent bombEvent(BombState bombState) {
    if (bombState != null) {

      return bombState.asRgbEvent();
    }
    return null;
  }

  // defines the team of the player and returns the RGB event
  private RgbEvent team(Team team) {
    if (team != null) {
      return team.asRgbEvent();
    }

    return null;
  }

  // defines the winner team and returns the rgb event
  private RgbEvent winnerTeam(Team winTeam, Team team) {

    if (winTeam == team) {
      return RgbEvent.ROUNDOVERWIN;
    } else {
      return RgbEvent.ROUNDOVERLOST;
    }
  }

  // ----------------------------- sorting

  // TODO check if there is a better way to sort this
  private List<RgbEvent> sortListByPriority(List<RgbEvent> srcList) {
    List<RgbEvent> sortedList = new ArrayList<>();
    int highestPriority = getHighestPriority(srcList);

    for (int i = 0; i <= highestPriority; i++) {
      for (RgbEvent event : srcList) {
        if (event.getPriority() == i) {
          sortedList.add(event);
        }
      }
    }

    return sortedList;
  }

  private int getHighestPriority(List<RgbEvent> list) {
    int highestPriority = 0;

    for (RgbEvent event : list) {
      if (highestPriority < event.getPriority()) {
        highestPriority = event.getPriority();
      }
    }

    return highestPriority;
  }
}
