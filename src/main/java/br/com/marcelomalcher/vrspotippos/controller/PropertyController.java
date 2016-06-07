package br.com.marcelomalcher.vrspotippos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.search.SearchPropertiesResult;
import br.com.marcelomalcher.vrspotippos.service.PropertyService;

@RestController
@RequestMapping(value = "/properties", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PropertyController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  PropertyService service;

  @RequestMapping(method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Property> create(@RequestBody @Valid Property property) {
    Property createdProperty = service.create(property);
    if (createdProperty != null) {
      return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    } else {
      return null;
    }
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id:.+}")
  public ResponseEntity<Property> read(@PathVariable Integer id) {
    Property property = service.read(id);
    if (property != null) {
      return new ResponseEntity<>(property, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.GET)
  public SearchPropertiesResult searchByBox(@RequestParam(value = "ax") Integer ax,
                                            @RequestParam(value = "ay") Integer ay,
                                            @RequestParam(value = "bx") Integer bx,
                                            @RequestParam(value = "by") Integer by) {
    return service.filterPropertiesByBox(ax, ay, bx, by);
  }
}
