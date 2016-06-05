package br.com.marcelomalcher.vrspotippos.service;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.domain.search.SearchPropertiesResult;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;
import br.com.marcelomalcher.vrspotippos.service.search.SearchPropertiesService;
import br.com.marcelomalcher.vrspotippos.service.search.SearchProvincesService;

@Service
public class PropertyService {

  @Autowired
  PropertyRepository repository;
  @Autowired
  ProvinceRepository provinceRepository;
  @Autowired
  SearchPropertiesService searchPropertiesService;
  @Autowired
  SearchProvincesService searchProvincesService;

  public Property create(Property property) {
    property.setProvinces(matchProvinces(property));
    return repository.create(property);
  }

  private Collection<String> matchProvinces(Property property) {
    List<String> provinceNames = Lists.newArrayList();

    Collection<Province> provinces = searchProvincesService.
      filterProvinces(provinceRepository.readAll(), property.getX(), property.getY());

    if (!StringUtils.isEmpty(provinces)) {
      provinces.stream().forEach(p -> provinceNames.add(p.getName()));
    }

    return provinceNames;
  }

  public Property read(String id) {
    return repository.read(id);
  }

  public Collection<Property> readAll() {
    return repository.readAll();
  }

  public SearchPropertiesResult searchPropertiesByBoundingBox(Integer ax, Integer ay,
                                                              Integer bx, Integer by) {
    Collection<Property> properties =
      searchPropertiesService.filterProperties(
        repository.readAll(),
        ax, ay, bx, by);
    return new SearchPropertiesResult.Builder()
      .withProperties(properties)
      .build();
  }
}
