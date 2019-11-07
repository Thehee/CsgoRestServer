package ch.olivo.leonardo.csgoRestServer.handler;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import ch.olivo.leonardo.csgoRestServer.converter.CsgoEventRequestToCsgoEventConverter;
import ch.olivo.leonardo.csgoRestServer.handler.domain.CsgoEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.Weapon;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.GrenadeType;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.WeaponState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventHandler {

  @Autowired
  private CsgoEventRequestToCsgoEventConverter csgoEventConverter;

  private CsgoEvent convert(CsgoEventRequest event) {
    return csgoEventConverter.convert(event);
  }

//  private boolean healthLost(CsgoEvent event) {
//    int previouslyHealth = 1000;
//    int health = event.getPlayer().getPlayerState().getHealth();
//    try {
//      previouslyHealth = event.getPreviously().getPlayer().getPlayerState().getHealth();
//    } catch (NullPointerException e) {
//      return false;
//    }
//
//    return previouslyHealth > health;
//  }

  public void handleEvent(CsgoEventRequest eventRequest) {
    CsgoEvent csgoEvent = convert(eventRequest);
    System.out.println(activeGrenade(csgoEvent.getPlayer().getWeapons()));

  }

  private GrenadeType activeGrenade(List<Weapon> weapons) {
    for (Weapon weapon : weapons) {
      if (weapon.getWeaponState() == WeaponState.ACTIVE) {
        return weapon.getGrenadeType();
      }
    }
    return null;
  }
}
