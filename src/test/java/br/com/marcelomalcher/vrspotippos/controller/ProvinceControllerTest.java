package br.com.marcelomalcher.vrspotippos.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.VRSpotipposApplication;
import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VRSpotipposApplication.class)
@WebIntegrationTest
public class ProvinceControllerTest {

  private final static Logger logger = LoggerFactory
    .getLogger(ProvinceControllerTest.class);

  TestRestTemplate testRestTemplate = new TestRestTemplate();

  @Autowired
  ObjectMapper mapper;

  @Autowired
  ProvinceRepository repository;

  @Autowired
  Environment environment;

  String port = "8080";

  @Before
  public void init() {
    this.port = environment.getProperty("local.server.port");
  }

  @Test
  public void testReadProvince() throws Exception {
    //given
    String name = UUID.randomUUID().toString();

    Province province = new Province();
    province.setName(name);
    Province.Boundaries upperLeft = new Province.Boundaries();
    upperLeft.setX(10);
    upperLeft.setY(20);
    province.setUpperLeft(upperLeft);
    Province.Boundaries bottomRight = new Province.Boundaries();
    upperLeft.setX(30);
    upperLeft.setY(40);
    province.setBottomRight(bottomRight);

    repository.create(province);

    ResponseEntity<String> responseEntity =
      testRestTemplate.getForEntity(
        "http://localhost:" + port + "/provinces/" + name,
        String.class);

    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

    String response = responseEntity.getBody();

    logger.debug("Controller response: \n{}", response);

    JsonNode jsonNode = mapper.readTree(response);

    Province foundProvince = mapper.treeToValue(jsonNode, Province.class);

    assertThat(foundProvince, is(notNullValue()));
    assertThat(foundProvince.getName(), is(name));
    assertThat(foundProvince.getUpperLeft(), is(notNullValue()));
    assertThat(foundProvince.getUpperLeft().getX(), is(province.getUpperLeft().getX()));
    assertThat(foundProvince.getUpperLeft().getY(), is(province.getUpperLeft().getY()));
    assertThat(foundProvince.getBottomRight(), is(notNullValue()));
    assertThat(foundProvince.getBottomRight().getX(), is(province.getBottomRight().getX()));
    assertThat(foundProvince.getBottomRight().getY(), is(province.getBottomRight().getY()));
  }
}
