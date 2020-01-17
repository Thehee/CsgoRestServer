package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ColorEventType {
  FLASH (1),
  DEFAULT (2),
  BLINKING (3),
  TWOLEDSTRING (4),
  THREELEDSTRING (5),
  FOURLEDSTRING (6);

  private final int commandNumber;

  /**
   * Finds the right Enum by a String
   * @param commandName is the name of the enum as String
   * @return ColorEventType which equals the param
   */
  public static ColorEventType byString(String commandName) {
    return Arrays.stream(ColorEventType.values())
        .filter(value -> value.name().toLowerCase().equals(commandName)).findFirst().orElse(null);
  }
}
