package br.com.marcelomalcher.vrspotippos.service.filter;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterPropertiesServiceTest {

  @Mock
  PropertyRepository repository;

  @InjectMocks
  FilterPropertiesService service;

  @Test
  public void shouldFilterProperties() throws IOException {
    //given
    Collection<Property> properties = Lists.newArrayList();

    //P1
    Property p1 = new Property();
    p1.setId(1);
    p1.setX(10);
    p1.setY(10);
    properties.add(p1);

    //P2
    Property p2 = new Property();
    p2.setId(2);
    p2.setX(20);
    p2.setY(20);
    properties.add(p2);

    //P3
    Property p3 = new Property();
    p3.setX(30);
    p3.setY(30);
    properties.add(p3);

    //P4
    Property p4 = new Property();
    p4.setX(40);
    p4.setY(40);
    properties.add(p4);

    //when
    when(repository.readAll()).thenReturn(properties);

    Collection<Property> filteredProperties =
      service.filterProperties(0, 35, 35, 0);

    //then
    //p4 is filtered out as it is outside the bounding box
    assertThat(filteredProperties.size(), is(3));
    assertThat(filteredProperties, hasItem(p1));
    assertThat(filteredProperties, hasItem(p2));
    assertThat(filteredProperties, hasItem(p3));
    assertThat(filteredProperties, not(hasItem(p4)));
  }
}