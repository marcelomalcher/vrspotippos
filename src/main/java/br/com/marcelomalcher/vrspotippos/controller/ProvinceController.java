package br.com.marcelomalcher.vrspotippos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  ProvinceRepository repository;


  @RequestMapping(
    method = RequestMethod.GET,
    value = "/{name:.+}",
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Province> read(@PathVariable String name) {
    Province province = repository.read(name);
    if (province != null) {
      return new ResponseEntity<>(province, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Province> readAll() {
    return repository.readAll();
  }
}
