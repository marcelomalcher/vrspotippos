package br.com.marcelomalcher.vrspotippos.repository;

import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.domain.Province;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class HashMapProvinceRepositoryTest {

  HashMapProvinceRepository repository = new HashMapProvinceRepository();

  @Test
  public void shouldCreateProvince() throws IOException {
    //given
    Province p = new Province();
    p.setName(UUID.randomUUID().toString());
    Province.Boundaries upperLeft = new Province.Boundaries();
    upperLeft.setX(10);
    upperLeft.setY(20);
    p.setUpperLeft(upperLeft);
    Province.Boundaries bottomRight = new Province.Boundaries();
    upperLeft.setX(30);
    upperLeft.setY(40);
    p.setBottomRight(bottomRight);

    //when
    Province createdProvince = repository.create(p);

    //then
    assertThat(repository.readAll().size(), greaterThan(0));
    assertThat(createdProvince, is(p));
    assertThat(createdProvince.getName(), is(p.getName()));
  }


  @Test
  public void shouldReadProvince() throws IOException {
    //given
    String pName = "provinceTEST";
    Province p = new Province();
    p.setName(pName);
    Province.Boundaries upperLeft = new Province.Boundaries();
    upperLeft.setX(10);
    upperLeft.setY(20);
    p.setUpperLeft(upperLeft);
    Province.Boundaries bottomRight = new Province.Boundaries();
    upperLeft.setX(30);
    upperLeft.setY(40);
    p.setBottomRight(bottomRight);

    //when
    repository.create(p);
    Province existingProvince = repository.read(pName.toLowerCase());

    //then
    assertThat(existingProvince, is(notNullValue()));
    assertThat(existingProvince.getName(), is(p.getName()));
  }

  @Test
  public void shouldReadAllProvinces() throws IOException {
    //given
    Integer max = 100;
    for (int i = 0; i < max; i++) {
      Province p = new Province();
      p.setName("Title :: " + i);
      repository.create(p);
    }

    //when
    Collection<Province> provinces = repository.readAll();

    //then
    assertThat(provinces.size(), is(max));
  }
}