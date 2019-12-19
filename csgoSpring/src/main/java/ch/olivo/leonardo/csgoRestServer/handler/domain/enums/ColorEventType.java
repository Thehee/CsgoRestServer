package ch.olivo.leonardo.csgoRestServer.handler.domain.enums;

import java.util.Arrays;

public enum ColorEventType {
  FLASH {
    @Override
    public int asCommandNumber() {
      return 1;
    }
  },
  DEFAULT {
    @Override
    public int asCommandNumber() {
      return 2;
    }
  },
  BLINKING {
    @Override
    public int asCommandNumber() {
      return 3;
    }
  },
  TWOLEDSTRING {
    @Override
    public int asCommandNumber() {
      return 4;
    }
  },
  THREELEDSTRING {
    @Override
    public int asCommandNumber() {
      return 5;
    }
  },
  FOURLEDSTRING {
    @Override
    public int asCommandNumber() {
      return 6;
    }
  };

  public int asCommandNumber() {
    return 0;
  }

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
