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
import br.com.marcelomalcher.vrspotippos.service.PropertyService;
import br.com.marcelomalcher.vrspotippos.service.reader.PropertiesReaderService;

@Service
public class PropertiesLoaderService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  String propertiesURL;
  RestTemplate restTemplate;
  PropertiesReaderService propertiesReader;
  PropertyService propertyService;

  @Autowired
  public PropertiesLoaderService(@Qualifier("properties") String propertiesURL,
                                 RestTemplate restTemplate,
                                 PropertiesReaderService propertiesReader,
                                 PropertyService propertyService) {
    this.propertiesURL = propertiesURL;
    this.restTemplate = restTemplate;
    this.propertiesReader = propertiesReader;
    this.propertyService = propertyService;
  }

  public void loadProperties() throws IOException {
    logger.debug("Loading properties from: {}", propertiesURL);
    ResponseEntity<String> propertiesJson =
      restTemplate.getForEntity(propertiesURL, String.class);
    //logger.debug("Properties json: {}", propertiesJson.getBody());
    Collection<Property> properties= propertiesReader.readProperties(propertiesJson.getBody());
    if (!StringUtils.isEmpty(properties)) {
      properties.stream()
        .forEach(p -> propertyService.create(p));
    }
  }
}
