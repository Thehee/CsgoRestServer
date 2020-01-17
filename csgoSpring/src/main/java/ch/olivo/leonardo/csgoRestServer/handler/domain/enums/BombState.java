package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BombState {
  EXPLODED(RgbEvent.EXPLODED),
  PLANTED (RgbEvent.PLANTED),
  DEFUSED (RgbEvent.DEFUSED);

  // default
  private final RgbEvent rgbEvent;

  public static BombState byString(String state) {
    return Arrays.stream(BombState.values())
        .filter(value -> value.name().toLowerCase().equals(state)).findFirst().orElse(null);
  }
}
