package br.com.marcelomalcher.vrspotippos.service.reader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import br.com.marcelomalcher.vrspotippos.domain.Property;

@Service
public class PropertiesReaderService {

  ObjectMapper mapper;

  @Autowired
  public PropertiesReaderService(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public Collection<Property> readProperties(String propertiesJson) throws IOException {
    JsonNode rootNode = mapper.readTree(propertiesJson);
    return readProperties(rootNode.path("properties"));
  }

  private Collection<Property> readProperties(JsonNode propertiesNode) throws IOException {
    JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Property.class);
    return mapper.readValue(propertiesNode.traverse(), type);

  }
}
