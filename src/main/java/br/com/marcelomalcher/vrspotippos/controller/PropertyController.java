package br.com.marcelomalcher.vrspotippos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.search.PropertiesSearchResult;
import br.com.marcelomalcher.vrspotippos.service.PropertiesSearchService;
import br.com.marcelomalcher.vrspotippos.service.PropertyCRUDService;

@RestController(value = "/properties")
public class PropertyController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  PropertyCRUDService crudService;
  @Autowired
  PropertiesSearchService searchService;

  @RequestMapping(
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Property> create(@RequestBody Property property) {
    Property createdProperty = crudService.create(property);
    if (createdProperty != null) {
      return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    } else {
      return null;
    }
  }

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Property> read(@RequestParam String id) {
    Property property = crudService.read(id);
    if (property != null) {
      return new ResponseEntity<>(property, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/ax={ax}&ay={ay}&bx={bx}&by={by}",
    produces = MediaType.APPLICATION_JSON_VALUE)
  public PropertiesSearchResult searchByBox(@RequestParam Integer ax, @RequestParam Integer ay,
                                            @RequestParam Integer bx, @RequestParam Integer by) {
    return searchService.searchByBox(ax, ay, bx, by);
  }
}
