package br.com.marcelomalcher.vrspotippos.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import br.com.marcelomalcher.vrspotippos.domain.Property;

@Repository
public class HashMapPropertyRepository implements PropertyRepository {

  private final ConcurrentHashMap<Integer, Property> map = new ConcurrentHashMap<>();

  private Integer controlId = 0;

  @Override
  public Property create(Property property) {
    if (property.getId() == null) {
      property.setId(generateId());
    } else {
      if (property.getId() > controlId) {
        controlId = property.getId();
      }
    }
    map.put(property.getId(), property);
    return property;
  }

  private synchronized Integer generateId() {
    return ++controlId;
  }

  @Override
  public Property read(Integer id) {
    return map.get(id);
  }

  @Override
  public Collection<Property> readAll() {
    return map.values();
  }
}
