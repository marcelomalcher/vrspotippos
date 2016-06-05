package br.com.marcelomalcher.vrspotippos.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;

@Service
public class SearchPropertiesService {

  @Autowired
  PropertyRepository repository;

  public Collection<Property> filterProperties(Collection<Property> properties,
                                               Integer ax, Integer ay,
                                               Integer bx, Integer by) {
    return properties
      .stream()
      .filter(
        p -> p != null &&
          p.getX() >= ax && p.getX() <= bx &&
          p.getY() >= by && p.getY() <= ay)
      .collect(Collectors.toList());
  }
}
