package br.com.marcelomalcher.vrspotippos.service.reader;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import br.com.marcelomalcher.vrspotippos.VRSpotipposApplication;
import br.com.marcelomalcher.vrspotippos.domain.Province;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PropertiesReaderServiceTest {

  ProvincesReaderService service = new ProvincesReaderService(new ObjectMapper());

  @Test
  public void shouldReadProvincesFromJson() throws IOException {
    //given
    Resource resource = new ClassPathResource("/samples/provinces.json");
    String provincesJson = IOUtils.toString(resource.getInputStream(), "UTF-8");

    //when
    Collection<Province> provinces = service.readProvinces(provincesJson);

    //then
    assertThat(provinces.size(), is(6));

    Iterator<Province> iterator = provinces.iterator();

    //.. Gode
    Province province = iterator.next();
    assertThat(province.getName(), is("Gode"));
    assertThat(province.getUpperLeft().getX(), is(0));
    assertThat(province.getUpperLeft().getY(), is(1000));
    assertThat(province.getBottomRight().getX(), is(600));
    assertThat(province.getBottomRight().getY(), is(500));

    //.. Ruja
    province = iterator.next();
    assertThat(province.getName(), is("Ruja"));
    assertThat(province.getUpperLeft().getX(), is(400));
    assertThat(province.getUpperLeft().getY(), is(1000));
    assertThat(province.getBottomRight().getX(), is(1100));
    assertThat(province.getBottomRight().getY(), is(500));

    //.. Jaby
    province = iterator.next();
    assertThat(province.getName(), is("Jaby"));
    assertThat(province.getUpperLeft().getX(), is(1100));
    assertThat(province.getUpperLeft().getY(), is(1000));
    assertThat(province.getBottomRight().getX(), is(1400));
    assertThat(province.getBottomRight().getY(), is(500));

    //.. Scavy
    province = iterator.next();
    assertThat(province.getName(), is("Scavy"));
    assertThat(province.getUpperLeft().getX(), is(0));
    assertThat(province.getUpperLeft().getY(), is(500));
    assertThat(province.getBottomRight().getX(), is(600));
    assertThat(province.getBottomRight().getY(), is(0));

    //.. Groola
    province = iterator.next();
    assertThat(province.getName(), is("Groola"));
    assertThat(province.getUpperLeft().getX(), is(600));
    assertThat(province.getUpperLeft().getY(), is(500));
    assertThat(province.getBottomRight().getX(), is(800));
    assertThat(province.getBottomRight().getY(), is(0));

    //.. Nova
    province = iterator.next();
    assertThat(province.getName(), is("Nova"));
    assertThat(province.getUpperLeft().getX(), is(800));
    assertThat(province.getUpperLeft().getY(), is(500));
    assertThat(province.getBottomRight().getX(), is(1400));
    assertThat(province.getBottomRight().getY(), is(0));
  }
}