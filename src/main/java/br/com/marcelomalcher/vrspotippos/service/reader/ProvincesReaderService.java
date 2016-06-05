package br.com.marcelomalcher.vrspotippos.service.reader;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.marcelomalcher.vrspotippos.domain.Province;

@Service
public class ProvincesReaderService {

  ObjectMapper mapper;

  @Autowired
  public ProvincesReaderService(ObjectMapper mapper) {
    this.mapper = mapper;
  }


  public Collection<Province> readProvinces(String provincesJson) throws IOException {
    List<Province> provinces = Lists.newArrayList();
    JsonNode rootNode = mapper.readTree(provincesJson);
    Iterator<Map.Entry<String, JsonNode>> iterator = rootNode.fields();
    while (iterator.hasNext()) {
      Map.Entry<String, JsonNode> entry = iterator.next();
      provinces.add(readProvince(entry.getKey(), entry.getValue()));
    }
    return provinces;
  }

  private Province readProvince(String name, JsonNode provinceNode) {
    Province province = new Province();
    province.setName(name);
    JsonNode boundaries = provinceNode.path("boundaries");
    province.setUpperLeft(readBoundaries(boundaries.path("upperLeft")));
    province.setBottomRight(readBoundaries(boundaries.path("bottomRight")));
    return province;
  }

  private Province.Boundaries readBoundaries(JsonNode boundariesNode) {
    Province.Boundaries boundaries = new Province.Boundaries();
    boundaries.setX(boundariesNode.path("x").asInt());
    boundaries.setY(boundariesNode.path("y").asInt());
    return boundaries;
  }
}
