package br.com.marcelomalcher.vrspotippos.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.naming.directory.SearchResult;

import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.domain.search.SearchPropertiesResult;
import br.com.marcelomalcher.vrspotippos.repository.PropertyRepository;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;
import br.com.marcelomalcher.vrspotippos.service.filter.FilterPropertiesService;
import br.com.marcelomalcher.vrspotippos.service.filter.FilterProvincesService;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceTest {

  @Mock
  PropertyRepository repository;
  @Mock
  ProvinceRepository provinceRepository;
  @Mock
  FilterPropertiesService filterPropertiesService;
  @Mock
  FilterProvincesService filterProvincesService;

  @InjectMocks
  PropertyService service;

  @Test
  public void shouldCreatePropertyWithProvinces() throws IOException {
    //given
    Property p = new Property();
    p.setTitle(UUID.randomUUID().toString());
    p.setX(10);
    p.setY(20);

    Province p1 = new Province();
    p1.setName("Province1::" + UUID.randomUUID().toString());

    Province p2 = new Province();
    p2.setName("Province2::" + UUID.randomUUID().toString());

    //when
    when(filterProvincesService.filterProvinces(p.getX(), p.getY()))
      .thenReturn(Arrays.asList(p1, p2));
    when(repository.create(p)).thenReturn(p);

    Property createdProperty = service.create(p);

    //then
    assertThat(createdProperty, is(notNullValue()));
    assertThat(createdProperty.getTitle(), is(p.getTitle()));
    assertThat(createdProperty.getProvinces(), hasItem(p1.getName()));
    assertThat(createdProperty.getProvinces(), hasItem(p2.getName()));
  }

  @Test
  public void shouldFilterPropertiesByBox() throws IOException {
    //given
    Property p1 = new Property();
    p1.setTitle(UUID.randomUUID().toString());

    Property p2 = new Property();
    p2.setTitle(UUID.randomUUID().toString());

    Property p3 = new Property();
    p3.setTitle(UUID.randomUUID().toString());

    //when
    when(filterPropertiesService.filterProperties(anyInt(), anyInt(), anyInt(), anyInt()))
      .thenReturn(Arrays.asList(p1, p2, p3));

    SearchPropertiesResult searchResult = service.filterPropertiesByBox(1, 2, 3, 4);

    //then
    assertThat(searchResult, is(notNullValue()));
    assertThat(searchResult.getFoundProperties(), is(3));
    assertThat(searchResult.getProperties(), hasItem(p1));
    assertThat(searchResult.getProperties(), hasItem(p2));
    assertThat(searchResult.getProperties(), hasItem(p3));
  }
}