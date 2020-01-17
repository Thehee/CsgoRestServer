package ch.olivo.leonardo.csgoRestServer.handler.converter;

import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Player;
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
public class CsgoEventToRgbEventConverter {

  // ----------------------------- rgb event collector
  // TODO REFACTOR PLEASE IT LOOKS DISGUSTING.
  //  Update 1: looks a little better

  /**
   * returns the event that should be ran
   * @param event the event that happened in the game
   * @return RgbEvent a certain rgb event that should be displayed by the LEDs
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

    if (event.getPreviously() != null) {

      eventsList.add(damageTaken(event.getPlayer(), event.getPreviously().getPlayer()));

      eventsList.add(shotsFired(event.getPlayer(), event.getPreviously().getPlayer()));
    }

    eventsList.removeAll(Collections.singleton(null));

    if (eventsList.size() != 0) {
      return sortListByPriority(eventsList).get(0);
    }

    return null;
  }

  // ----------------------------- rgb event definer classes

  // returns the active nade if there is any
  private RgbEvent activeNade(List<Weapon> weapons) {
    if (weapons == null) {
      return null;
    }
    for (Weapon weapon : weapons) {
      if (weapon.getWeaponState().isActive() && weapon.getGrenadeType() != null) {
        return weapon.getGrenadeType().getRgbEvent();
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

        if (round.getWinTeam() != null) {
          events.add(winnerTeam(round.getWinTeam(), team));
        }
        break;

      case LIVE:
        if (round.getBomb() != null) {
          events.add(round.getBomb().getRgbEvent());
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

      return bombState.getRgbEvent();
    }
    return null;
  }

  // defines the team of the player and returns the RGB event
  private RgbEvent team(Team team) {
    if (team != null) {
      return team.getRgbEvent();
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

  // defines if damage has been taken
  private RgbEvent damageTaken(Player player, Player previouslyPlayer) {
    if (previouslyPlayer == null || previouslyPlayer.getPlayerState() == null) {
      return null;
    }

    if (player.getPlayerState().getHealth() < previouslyPlayer.getPlayerState().getHealth()) {
      return RgbEvent.DAMAGE;
    }
    return null;
  }

  // returns the right event if a shot has been fired
  private RgbEvent shotsFired(Player player, Player previouslyPlayer) {
    if (previouslyPlayer == null || previouslyPlayer.getWeapons() == null || previouslyPlayer.getWeapons().size() != 1
        || previouslyPlayer.getWeapons().get(0).getAmmo_clip() == null
    ) {
      return null;
    }

    Weapon affectedWeapon = findByIndex(player.getWeapons(), previouslyPlayer.getWeapons().get(0));
    Weapon previouslyWeapon = previouslyPlayer.getWeapons().get(0);

    if (affectedWeapon == null || previouslyWeapon.getAmmo_clip() == null) {
      return null;
    }

    if (hasShot(affectedWeapon, previouslyWeapon)) {
      return defineEventByType(affectedWeapon);
    }

    return null;
  }

  // checks if the gun was shot
  private boolean hasShot(Weapon weapon, Weapon previouslyWeapon) {
    return previouslyWeapon.getAmmo_clip() > weapon.getAmmo_clip();
  }

  // defines the event from the type of gun
  private RgbEvent defineEventByType(Weapon weapon) {
    switch (weapon.getType()) {
      case RIFLE:
      case PISTOL:
        return RgbEvent.MEDIUMLINE;
      case SHOTGUN:
      case SNIPERRIFLE:
        return RgbEvent.LONGLINE;
      case MACHINEGUN:
      case SUBMACHINEGUN:
        return RgbEvent.SHORTLINE;
      case TASER:
        return RgbEvent.TASERSHOT;
      default:
        return null;
    }
  }

  // finds the right weapon by the index of the previously weapon
  private Weapon findByIndex(List<Weapon> weapons, Weapon previouslyWeapon) {
    int index = previouslyWeapon.getIndex();

    for (Weapon weapon : weapons) {
      if (weapon.getIndex() == index) {
        return weapon;
      }
    }
    return null;
  }

  // ----------------------------- sorting

  // sorts the list by the priority of the events
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

  // gets the highest priority from hte list of events
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