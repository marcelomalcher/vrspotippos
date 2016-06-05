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
import br.com.marcelomalcher.vrspotippos.domain.Property;
import br.com.marcelomalcher.vrspotippos.domain.Province;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProvincesReaderServiceTest {

  PropertiesReaderService service = new PropertiesReaderService(new ObjectMapper());

  @Test
  public void shouldReadPropertiesFromJson() throws IOException {
    //given
    Resource resource = new ClassPathResource("/samples/properties.json");
    String propertiesJson = IOUtils.toString(resource.getInputStream(), "UTF-8");

    //when
    Collection<Property> properties = service.readProperties(propertiesJson);

    //then
    assertThat(properties.size(), is(10));
  }
}