package br.com.marcelomalcher.vrspotippos.service.filter;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterProvincesServiceTest {

  @Mock
  ProvinceRepository repository;

  @InjectMocks
  FilterProvincesService service;

  Collection<Province> provinces;
  String province1 = UUID.randomUUID().toString();
  String province2 = UUID.randomUUID().toString();
  String province3 = UUID.randomUUID().toString();
  String province4 = UUID.randomUUID().toString();

  @Before
  public void init() {
    createProvinces();
  }

  private void createProvinces() {
    provinces = Lists.newArrayList();

    // 4 provinces
    // - x from 0 to 90
    // - y from 0 to 90

    //P1 - UL(0,50) BR(50,0)
    Province p1 = new Province();
    p1.setName(province1);
    Province.Boundaries upperLeft1 = new Province.Boundaries();
    upperLeft1.setX(0);
    upperLeft1.setY(50);
    p1.setUpperLeft(upperLeft1);
    Province.Boundaries bottomRight1 = new Province.Boundaries();
    bottomRight1.setX(50);
    bottomRight1.setY(0);
    p1.setBottomRight(bottomRight1);

    provinces.add(p1);

    //P2 - UL(40,50) BR(90,0)
    Province p2 = new Province();
    p2.setName(province2);
    Province.Boundaries upperLeft2 = new Province.Boundaries();
    upperLeft2.setX(40);
    upperLeft2.setY(50);
    p2.setUpperLeft(upperLeft2);
    Province.Boundaries bottomRight2 = new Province.Boundaries();
    bottomRight2.setX(90);
    bottomRight2.setY(0);
    p2.setBottomRight(bottomRight2);

    provinces.add(p2);

    //P3 - UL(0,90) BR(50,40)
    Province p3 = new Province();
    p3.setName(province3);
    Province.Boundaries upperLeft3 = new Province.Boundaries();
    upperLeft3.setX(0);
    upperLeft3.setY(90);
    p3.setUpperLeft(upperLeft3);
    Province.Boundaries bottomRight3 = new Province.Boundaries();
    bottomRight3.setX(50);
    bottomRight3.setY(40);
    p3.setBottomRight(bottomRight3);

    provinces.add(p3);

    //P4 - UL(40,90) BR(90,40)
    Province p4 = new Province();
    p4.setName(province4);
    Province.Boundaries upperLeft4 = new Province.Boundaries();
    upperLeft4.setX(40);
    upperLeft4.setY(90);
    p4.setUpperLeft(upperLeft4);
    Province.Boundaries bottomRight4 = new Province.Boundaries();
    bottomRight4.setX(90);
    bottomRight4.setY(40);
    p4.setBottomRight(bottomRight4);

    provinces.add(p4);
  }

  @Test
  public void shouldMatchOneProvince1() throws IOException {
    //when
    when(repository.readAll()).thenReturn(provinces);

    Collection<Province> filterProvinces =
      service.filterProvinces(10, 10);

    //then
    assertThat(filterProvinces.size(), is(1));

    Province p = filterProvinces.iterator().next();

    assertThat(p.getName(), is(province1));
  }

  @Test
  public void shouldMatchProvinces1And2() throws IOException {
    //when
    when(repository.readAll()).thenReturn(provinces);

    Collection<Province> filterProvinces =
      service.filterProvinces(45, 20);

    //then
    assertThat(filterProvinces.size(), is(2));

    Iterator<Province> iterator = filterProvinces.iterator();

    assertThat(iterator.next().getName(), is(province1));
    assertThat(iterator.next().getName(), is(province2));
  }

  @Test
  public void shouldMatchAllProvinces() throws IOException {
    //when
    when(repository.readAll()).thenReturn(provinces);

    Collection<Province> filterProvinces =
      service.filterProvinces(45, 45);

    //then
    assertThat(filterProvinces.size(), is(4));

    Iterator<Province> iterator = filterProvinces.iterator();

    assertThat(iterator.next().getName(), is(province1));
    assertThat(iterator.next().getName(), is(province2));
    assertThat(iterator.next().getName(), is(province3));
    assertThat(iterator.next().getName(), is(province4));
  }


  @Test
  public void shouldMatchNoProvinces() throws IOException {
    //when
    when(repository.readAll()).thenReturn(provinces);

    Collection<Province> filterProvinces =
      service.filterProvinces(100, 100); //out of the range!

    //then
    assertThat(filterProvinces.size(), is(0));
  }
}