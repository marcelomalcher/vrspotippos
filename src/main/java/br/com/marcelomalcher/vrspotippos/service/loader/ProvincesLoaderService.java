package br.com.marcelomalcher.vrspotippos.service.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;
import br.com.marcelomalcher.vrspotippos.service.reader.ProvincesReaderService;

@Service
public class ProvincesLoaderService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  @Qualifier("provinces")
  String provincesURL;

  @Autowired
  ProvincesReaderService reader;

  @Autowired
  ProvinceRepository repository;

  public void loadProvinces() throws IOException {
    logger.debug("Loading provinces from: {}", provincesURL);
    ResponseEntity<String> provincesJson =
      restTemplate.getForEntity(provincesURL, String.class);
    logger.debug("Provinces json: {}", provincesJson.getBody());
    Collection<Province> provinces  = reader.readProvinces(provincesJson.getBody());
    if (!StringUtils.isEmpty(provinces)) {
      provinces.stream()
        .forEach(p -> repository.create(p));
    }
  }
}
