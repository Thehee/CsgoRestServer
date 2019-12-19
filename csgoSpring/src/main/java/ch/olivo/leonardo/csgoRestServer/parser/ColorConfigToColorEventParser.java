package ch.olivo.leonardo.csgoRestServer.parser;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import ch.olivo.leonardo.csgoRestServer.handler.domain.enums.ColorEventType;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ColorConfigToColorEventParser {
  public Map<String, ColorEvent> parse(Map<String, Map> colorConfig) {

    return colorConfig.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> createNewColorEvent(getColor(((Map<String, Map>) entry.getValue()).get("color")),
            getColor(((Map<String, Map>) entry.getValue()).get("secondColor")), entry.getValue().get("command").toString())));

  }

  private Color getColor(Map<String, Integer> colorMap) {
    return colorMap == null ? null : new Color(colorMap.get("r"), colorMap.get("g"), colorMap.get("b"));
  }

  private ColorEvent createNewColorEvent(Color color, Color secondColor, String command) {
    return new ColorEvent(color, secondColor, ColorEventType.byString(command));
  }

}
