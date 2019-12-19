package ch.olivo.leonardo.csgoRestServer.service;

import ch.olivo.leonardo.csgoRestServer.handler.domain.ColorEvent;
import ch.olivo.leonardo.csgoRestServer.parser.ColorConfigToColorEventParser;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class GetColorConfigYml {

  public static Map<String, ColorEvent> colorEventMap;
  private final String propFileName = "color_config.yml";

  @Autowired
  private ColorConfigToColorEventParser colorEventParser;

  /**
   * Gets the data out of the color_config.yml file
   */
  @PostConstruct
  public void getConfigValues() throws IOException {
    Yaml yaml = new Yaml();

    @Cleanup
    InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream(propFileName);

    colorEventMap = colorEventParser.parse(yaml.load(inputStream));
  }

  /**
   * gets the correct ColorEvent by the key
   * @param event is the key to get the correct ColorEvent
   * @return ColorEvent
   */
  public static ColorEvent getColorEvent(String event) {
    return colorEventMap.get(event);
  }
}
