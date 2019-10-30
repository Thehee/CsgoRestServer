package ch.olivo.leonardo.csgoRestServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class CsgoController {

  @RequestMapping(path = "/api", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Void> getJson(@RequestBody String jsonString) {
//    System.out.println(jsonString);
    Logger logger = LoggerFactory.getLogger(CsgoController.class);
    logger.info(jsonString);
    return ResponseEntity.noContent().build();
  }
}
