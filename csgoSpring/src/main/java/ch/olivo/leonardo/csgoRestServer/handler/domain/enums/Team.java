package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Team {
  T (RgbEvent.T),
  CT (RgbEvent.CT);

  // default
  private final RgbEvent rgbEvent;

  public static Team byString(String state) {
    return Arrays.stream(Team.values())
        .filter(value -> value.name().equals(state)).findFirst().orElse(null);
  }
}
