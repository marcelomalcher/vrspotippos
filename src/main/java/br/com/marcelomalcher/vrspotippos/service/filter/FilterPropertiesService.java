package br.com.marcelomalcher.vrspotippos.service.filter;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;

@Service
public class FilterPropertiesService {

  @Autowired
  PropertyRepository repository;

  public Collection<Property> filterProperties(Integer ax, Integer ay,
                                               Integer bx, Integer by) {
    Collection<Property> properties = repository.readAll();
    if (!StringUtils.isEmpty(properties)) {
      return properties
        .stream()
        .filter(
          p -> p != null &&
            p.getX() >= ax && p.getX() <= bx &&
            p.getY() >= by && p.getY() <= ay)
        .collect(Collectors.toList());
    }
    return Lists.newArrayList();
  }
}
