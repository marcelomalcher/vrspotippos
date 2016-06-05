package br.com.marcelomalcher.vrspotippos.service.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;
import br.com.marcelomalcher.vrspotippos.service.PropertyService;
import br.com.marcelomalcher.vrspotippos.service.reader.PropertiesReaderService;
import br.com.marcelomalcher.vrspotippos.service.reader.ProvincesReaderService;

@Service
public class PropertiesLoaderService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  @Qualifier("properties")
  String propertiesURL;

  @Autowired
  PropertiesReaderService reader;

  @Autowired
  PropertyService service;

  public void loadProperties() throws IOException {
    logger.debug("Loading properties from: {}", propertiesURL);
    ResponseEntity<String> propertiesJson =
      restTemplate.getForEntity(propertiesURL, String.class);
    //logger.debug("Properties json: {}", propertiesJson.getBody());
    Collection<Property> properties= reader.readProperties(propertiesJson.getBody());
    if (!StringUtils.isEmpty(properties)) {
      properties.stream()
        .forEach(p -> service.create(p));
    }
  }
}
