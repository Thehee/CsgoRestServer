package ch.olivo.leonardo.csgoRestServer.service;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import ch.olivo.leonardo.csgoRestServer.parser.ColorConfigToColorEventParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Map;

@Component
public class GetColorConfigYml {

  public static Map<String, ColorEvent> colorEventMap;
  private final String propFileName = "color_config.yml";

  @Autowired
  private ColorConfigToColorEventParser colorEventParser;

  @PostConstruct
  public void getConfigValues() {
    Yaml yaml = new Yaml();
    InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream(propFileName);

    colorEventMap = colorEventParser.parse(yaml.load(inputStream));
  }

  public static ColorEvent getColorEvent(String event) {
    return colorEventMap.get(event);
  }
}
