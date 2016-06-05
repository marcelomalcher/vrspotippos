package br.com.marcelomalcher.vrspotippos.repository;

import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Property;

public interface PropertyRepository {

  Property create(Property property);
  Property read(String id);
  Collection<Property> readAll();
}
