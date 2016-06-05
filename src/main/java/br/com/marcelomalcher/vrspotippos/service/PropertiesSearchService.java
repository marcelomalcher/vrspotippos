package br.com.marcelomalcher.vrspotippos.service;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.search.PropertiesSearchResult;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;

@Service
public class PropertiesSearchService {

  @Autowired
  PropertyRepository propertyRepository;

  public PropertiesSearchResult searchByBox(Integer ax, Integer ay,
                                            Integer bx, Integer by) {
    return new PropertiesSearchResult.Builder()
      .withProperties(filterProperties(ax, ay, bx, by))
      .build();
  }

  private Collection<Property> filterProperties(Integer ax, Integer ay,
                                                Integer bx, Integer by) {
    List<Property> properties = Lists.newArrayList(propertyRepository.readAll());
    return properties
      .stream()
      .filter(
        p -> p != null &&
        p.getX() >= ax && p.getX() <= bx &&
        p.getY() >= ay && p.getY() <= by)
      .collect(Collectors.toList());
  }
}
