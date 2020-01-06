package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum BombState {

  //Review PKE: Habe mir erlaubt das mal so umzustellen. Was würde gebgen diese Lösung sprechen?

  EXPLODED(RgbEvent.EXPLOADED),
  PLANTED(RgbEvent.PLANTED),
  DEFUSED(RgbEvent.DEFUSED);

  private final RgbEvent rgbEvent;

  BombState(RgbEvent rgbEvent) {
    this.rgbEvent = rgbEvent;
  }

  public RgbEvent getRgbEvent() {
    return rgbEvent;
  }

  public static BombState byString(String state) {
    return Arrays.stream(BombState.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
