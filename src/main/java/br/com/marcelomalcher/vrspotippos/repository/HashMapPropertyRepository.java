package br.com.marcelomalcher.vrspotippos.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import br.com.marcelomalcher.vrspotippos.domain.Property;

@Repository
public class HashMapPropertyRepository implements PropertyRepository {

  private final ConcurrentHashMap<String, Property> map = new ConcurrentHashMap<>();

  @Override
  public Property create(Property property) {
    return map.put(property.getId(), property);
  }

  @Override
  public Property read(String id) {
    return map.get(id);
  }

  @Override
  public Collection<Property> readAll() {
    return map.values();
  }
}
