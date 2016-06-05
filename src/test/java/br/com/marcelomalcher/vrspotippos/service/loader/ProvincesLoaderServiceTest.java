package br.com.marcelomalcher.vrspotippos.service.loader;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;
import br.com.marcelomalcher.vrspotippos.service.reader.ProvincesReaderService;

import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProvincesLoaderServiceTest {

  String provinceURL = "provinces.url";
  @Mock
  RestTemplate restTemplate;
  @Mock
  ProvincesReaderService provincesReader;
  @Mock
  ProvinceRepository provinceRepository;

  ProvincesLoaderService service;

  @Before
  public void init() {
    service = new ProvincesLoaderService(
      provinceURL, restTemplate, provincesReader, provinceRepository);
  }

  @Test
  public void shouldSaveLoadedProvinces() throws IOException {
    //given
    Collection<Province> provinces = Lists.newArrayList();

    Province p1 = new Province();
    p1.setName(UUID.randomUUID().toString());
    provinces.add(p1);

    Province p2 = new Province();
    p2.setName(UUID.randomUUID().toString());
    provinces.add(p2);

    Province p3 = new Province();
    p3.setName(UUID.randomUUID().toString());
    provinces.add(p3);

    ResponseEntity<String> responseEntity = ResponseEntity.ok(UUID.randomUUID().toString());

    //when
    when(restTemplate.getForEntity(provinceURL, String.class)).thenReturn(responseEntity);
    when(provincesReader.readProvinces(anyString())).thenReturn(provinces);

    service.loadProvinces();

    //then
    then(provinceRepository).should().create(p1);
    then(provinceRepository).should().create(p2);
    then(provinceRepository).should().create(p3);
  }

  @Test
  public void shouldNotSaveWhenProvincesWereNotLoaded() throws IOException {
    //given
    ResponseEntity<String> responseEntity = ResponseEntity.ok(UUID.randomUUID().toString());

    //when
    when(restTemplate.getForEntity(provinceURL, String.class)).thenReturn(responseEntity);
    when(provincesReader.readProvinces(anyString())).thenReturn(Lists.newArrayList());

    service.loadProvinces();

    //then
    then(provinceRepository).should(times(0)).create(any(Province.class));
  }
}