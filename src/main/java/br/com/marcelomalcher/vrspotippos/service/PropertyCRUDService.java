package br.com.marcelomalcher.vrspotippos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.search.PropertiesSearchResult;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;

@Service
public class PropertyCRUDService {

  @Autowired
  PropertyRepository repository;

  public Property create(Property property) {
    //identificar quais são as províncias que a propriedade pertence
    return repository.create(property);
  }

  public Property read(String id) {
    return repository.read(id);
  }
}
