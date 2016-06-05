package br.com.marcelomalcher.vrspotippos.service.loader;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.service.PropertyService;
import br.com.marcelomalcher.vrspotippos.service.reader.PropertiesReaderService;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesLoaderServiceTest {

  String propertiesURL = "properties.url";
  @Mock
  RestTemplate restTemplate;
  @Mock
  PropertiesReaderService propertiesReader;
  @Mock
  PropertyService propertyService;

  PropertiesLoaderService service;

  @Before
  public void init() {
    service = new PropertiesLoaderService(
      propertiesURL, restTemplate, propertiesReader, propertyService);
  }

  @Test
  public void shouldSaveLoadedProperties() throws IOException {
    //given
    Collection<Property> properties = Lists.newArrayList();

    Property p1 = new Property();
    p1.setId(1);
    p1.setTitle(UUID.randomUUID().toString());
    properties.add(p1);

    Property p2 = new Property();
    p2.setId(2);
    p2.setTitle(UUID.randomUUID().toString());
    properties.add(p2);

    Property p3 = new Property();
    p3.setId(3);
    p3.setTitle(UUID.randomUUID().toString());
    properties.add(p3);

    ResponseEntity<String> responseEntity = ResponseEntity.ok(UUID.randomUUID().toString());

    //when
    when(restTemplate.getForEntity(propertiesURL, String.class)).thenReturn(responseEntity);
    when(propertiesReader.readProperties(anyString())).thenReturn(properties);

    service.loadProperties();

    //then
    then(propertyService).should().create(p1);
    then(propertyService).should().create(p2);
    then(propertyService).should().create(p3);
  }

  @Test
  public void shouldNotSaveWhenPropertiesWereNotLoaded() throws IOException {
    //given
    ResponseEntity<String> responseEntity = ResponseEntity.ok(UUID.randomUUID().toString());

    //when
    when(restTemplate.getForEntity(propertiesURL, String.class)).thenReturn(responseEntity);
    when(propertiesReader.readProperties(anyString())).thenReturn(Lists.newArrayList());

    service.loadProperties();

    //then
    then(propertyService).should(times(0)).create(any(Property.class));
  }
}