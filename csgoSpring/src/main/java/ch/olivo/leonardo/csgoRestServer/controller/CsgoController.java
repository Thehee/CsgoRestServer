package ch.olivo.leonardo.csgoRestServer.controller;

import ch.olivo.leonardo.csgoRestServer.controller.msg.CsgoEventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
@Slf4j
public class CsgoController {


  @RequestMapping(path = "/api", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Void> handleJson(@RequestBody String jsonString) {
//    System.out.println(jsonString);
    log.info(jsonString);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(path = "/api2", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Void> handleEvent(@RequestBody CsgoEventRequest csgoEventRequest) {
//    System.out.println(jsonString);
    log.info("nice");
    return ResponseEntity.noContent().build();
  }
}
