package br.com.marcelomalcher.vrspotippos.repository;

import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.domain.Property;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class HashMapPropertyRepositoryTest {

  HashMapPropertyRepository repository = new HashMapPropertyRepository();

  @Test
  public void shouldCreatePropertyWithId() throws IOException {
    //given
    Property p = new Property();
    p.setId(1);
    p.setTitle("Title :: " + UUID.randomUUID().toString());
    p.setX(10);
    p.setY(10);

    //when
    Property createdProperty = repository.create(p);

    //then
    assertThat(repository.readAll().size(), greaterThan(0));
    assertThat(createdProperty, is(p));
    assertThat(createdProperty.getId(), is(p.getId()));
  }

  @Test
  public void shouldCreatePropertyWithoutId() throws IOException {
    //given
    Property p = new Property();
    p.setTitle("Title :: " + UUID.randomUUID().toString());
    p.setX(10);
    p.setY(10);

    //when
    Property createdProperty = repository.create(p);

    //then
    assertThat(repository.readAll().size(), greaterThan(0));
    assertThat(createdProperty, is(p));
    assertThat(createdProperty.getId(), is(notNullValue()));
  }

  @Test
  public void shouldReadProperty() throws IOException {
    //given
    Integer id = 1;
    Property p = new Property();
    p.setId(id);
    p.setTitle("Title :: " + UUID.randomUUID().toString());
    p.setX(10);
    p.setY(10);

    //when
    repository.create(p);
    Property existingProperty = repository.read(id);

    //then
    assertThat(existingProperty, is(notNullValue()));
    assertThat(existingProperty.getTitle(), is(p.getTitle()));
  }

  @Test
  public void shouldReadAllProperties() throws IOException {
    //given
    Integer max = 100;
    for (int i = 0; i < max; i++) {
      Property p = new Property();
      p.setTitle("Title :: " + i);
      repository.create(p);
    }

    //when
    Collection<Property> properties = repository.readAll();

    //then
    assertThat(properties.size(), is(max));
  }
}