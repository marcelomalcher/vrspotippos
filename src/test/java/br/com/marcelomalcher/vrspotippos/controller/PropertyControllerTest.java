package br.com.marcelomalcher.vrspotippos.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.VRSpotipposApplication;
import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.search.SearchPropertiesResult;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VRSpotipposApplication.class)
@WebIntegrationTest
public class PropertyControllerTest {

  private final static Logger logger = LoggerFactory
    .getLogger(PropertyControllerTest.class);

  TestRestTemplate testRestTemplate = new TestRestTemplate();

  @Autowired
  ObjectMapper mapper;

  @Autowired
  PropertyRepository repository;

  @Before
  public void init() {
    repository.clear();
  }

  @Test
  public void testCreateProperty() throws Exception {
    //given
    Property property = new Property();
    property.setX(222);
    property.setY(444);
    property.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
    property.setPrice(1250000l);
    property.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
    property.setBeds(4);
    property.setBaths(3);
    property.setSquareMeters(210);

    JsonNode jsonNode = mapper.valueToTree(property);

    //when
    ResponseEntity<String> responseEntity =
      testRestTemplate.postForEntity(
        "http://localhost:8080/properties",
        jsonNode,
        String.class);

    //then
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));

    String response = responseEntity.getBody();

    logger.debug("Controller response: \n{}", response);

    jsonNode = mapper.readTree(response);

    Property createdProperty = mapper.treeToValue(jsonNode, Property.class);

    assertThat(createdProperty, is(notNullValue()));
    assertThat(createdProperty.getId(), is(notNullValue()));
    assertThat(createdProperty.getX(), is(property.getX()));
    assertThat(createdProperty.getY(), is(property.getY()));
    assertThat(createdProperty.getTitle(), is(property.getTitle()));
    assertThat(createdProperty.getPrice(), is(property.getPrice()));
    assertThat(createdProperty.getDescription(), is(property.getDescription()));
    assertThat(createdProperty.getBeds(), is(property.getBeds()));
    assertThat(createdProperty.getBaths(), is(property.getBaths()));
    assertThat(createdProperty.getSquareMeters(), is(property.getSquareMeters()));
  }

  @Test
  public void testCreateInvalidProperty() throws Exception {
    //given
    Property property = new Property();
    property.setX(100000);
    property.setY(-10000);
    property.setBeds(50);
    property.setBaths(40);
    property.setSquareMeters(4000);

    JsonNode jsonNode = mapper.valueToTree(property);

    //when
    ResponseEntity<String> responseEntity =
      testRestTemplate.postForEntity(
        "http://localhost:8080/properties",
        jsonNode,
        String.class);

    //then
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }

  @Test
  public void testReadProperty() throws Exception {
    //given
    Integer id = 100;

    Property property = new Property();
    property.setId(id);
    property.setX(10);
    property.setY(20);
    property.setTitle("Imóvel " + UUID.randomUUID().toString());
    property.setPrice(1000l);
    property.setBeds(2);
    property.setBaths(2);
    property.setSquareMeters(150);

    repository.create(property);

    //when
    ResponseEntity<String> responseEntity =
      testRestTemplate.getForEntity(
        "http://localhost:8080/properties/" + id.toString(),
        String.class);

    //then
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

    String response = responseEntity.getBody();

    logger.debug("Controller response: \n{}", response);

    JsonNode jsonNode = mapper.readTree(response);

    Property foundProperty = mapper.treeToValue(jsonNode, Property.class);

    assertThat(foundProperty, is(notNullValue()));
    assertThat(foundProperty.getId(), is(id));
    assertThat(foundProperty.getX(), is(property.getX()));
    assertThat(foundProperty.getY(), is(property.getY()));
    assertThat(foundProperty.getTitle(), is(property.getTitle()));
    assertThat(foundProperty.getPrice(), is(property.getPrice()));
    assertThat(foundProperty.getBeds(), is(property.getBeds()));
    assertThat(foundProperty.getBaths(), is(property.getBaths()));
    assertThat(foundProperty.getSquareMeters(), is(property.getSquareMeters()));
  }

  @Test
  public void testSearchProperties() throws Exception {
    //given
    //..creates property 1,1
    Property p0 = new Property();
    p0.setId(0);
    p0.setX(1);
    p0.setY(1);
    p0.setBeds(1);
    p0.setBaths(1);
    p0.setSquareMeters(100);
    repository.create(p0);

    //..creates property 10,10
    Property p1 = new Property();
    p1.setId(1);
    p1.setX(10);
    p1.setY(10);
    p1.setBeds(1);
    p1.setBaths(1);
    p1.setSquareMeters(100);
    repository.create(p1);

    //..creates property 20,20
    Property p2 = new Property();
    p2.setId(2);
    p2.setX(20);
    p2.setY(20);
    p2.setBeds(1);
    p2.setBaths(1);
    p2.setSquareMeters(100);
    repository.create(p2);

    //..creates property 30,30
    Property p3 = new Property();
    p3.setId(3);
    p3.setX(30);
    p3.setY(30);
    p3.setBeds(1);
    p3.setBaths(1);
    p3.setSquareMeters(100);
    repository.create(p3);

    //..creates property 40,40
    Property p4 = new Property();
    p4.setId(4);
    p4.setX(40);
    p4.setY(40);
    p4.setBeds(1);
    p4.setBaths(1);
    p4.setSquareMeters(100);
    repository.create(p4);

    //when
    //..box defined as UL(0,100) and BR(100,0)
    ResponseEntity<String> responseEntity =
      testRestTemplate.getForEntity(
        "http://localhost:8080/properties?ax=0&ay=100&bx=100&by=0",
        String.class);

    //then
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

    String response = responseEntity.getBody();

    logger.debug("Controller response: \n{}", response);

    JsonNode jsonNode = mapper.readTree(response);

    SearchPropertiesResult searchPropertiesResult = mapper.treeToValue(jsonNode, SearchPropertiesResult.class);

    assertThat(searchPropertiesResult, is(notNullValue()));
    assertThat(searchPropertiesResult.getFoundProperties(), is(5));
    assertThat(searchPropertiesResult.getProperties().size(), is(5));
    assertThat(searchPropertiesResult.getProperties(), hasItem(p0));
    assertThat(searchPropertiesResult.getProperties(), hasItem(p1));
    assertThat(searchPropertiesResult.getProperties(), hasItem(p2));
    assertThat(searchPropertiesResult.getProperties(), hasItem(p3));
    assertThat(searchPropertiesResult.getProperties(), hasItem(p4));
  }
}
