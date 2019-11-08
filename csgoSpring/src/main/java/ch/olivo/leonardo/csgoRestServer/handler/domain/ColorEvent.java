package ch.olivo.leonardo.csgoRestServer.handler.domain;

import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.ColorEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
public class ColorEvent {
  private Color color;
  private Color secondColor;
  private ColorEventType colorEventType;
}
