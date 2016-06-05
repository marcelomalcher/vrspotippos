package br.com.marcelomalcher.vrspotippos.service;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.domain.search.SearchPropertiesResult;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;
import br.com.marcelomalcher.vrspotippos.service.filter.FilterPropertiesService;
import br.com.marcelomalcher.vrspotippos.service.filter.FilterProvincesService;

/**
 * Serviço responsável por criar as proprieades e buscar imóveis contiduos em uma determinada área
 */
@Service
public class PropertyService {

  @Autowired
  PropertyRepository repository;
  @Autowired
  ProvinceRepository provinceRepository;
  @Autowired
  FilterPropertiesService filterPropertiesService;
  @Autowired
  FilterProvincesService filterProvincesService;

  public Property create(Property property) {
    property.setProvinces(matchProvinces(property));
    return repository.create(property);
  }

  private Collection<String> matchProvinces(Property property) {
    Collection<Province> provinces = filterProvincesService.
      filterProvinces(property.getX(), property.getY());
    if (!StringUtils.isEmpty(provinces)) {
      return provinces.stream().map(p -> p.getName()).collect(Collectors.toList());
    }
    return Lists.newArrayList();
  }

  public Property read(Integer id) {
    return repository.read(id);
  }

  public Collection<Property> readAll() {
    return repository.readAll();
  }

  public SearchPropertiesResult filterPropertiesByBox(Integer ax, Integer ay,
                                                      Integer bx, Integer by) {
    Collection<Property> properties =
      filterPropertiesService.filterProperties(ax, ay, bx, by);
    return new SearchPropertiesResult.Builder()
      .withProperties(properties)
      .build();
  }
}
