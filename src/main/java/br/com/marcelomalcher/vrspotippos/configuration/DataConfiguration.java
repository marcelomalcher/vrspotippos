package br.com.marcelomalcher.vrspotippos.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="data")
public class DataConfiguration {

  private String provincesUrl;
  private String propertiesUrl;

  public String getProvincesUrl() {
    return provincesUrl;
  }

  public void setProvincesUrl(String provincesUrl) {
    this.provincesUrl = provincesUrl;
  }

  public String getPropertiesUrl() {
    return propertiesUrl;
  }

  public void setPropertiesUrl(String propertiesUrl) {
    this.propertiesUrl = propertiesUrl;
  }

  @Bean
  @Qualifier("provinces")
  public String provincesUrl() {
    return provincesUrl;
  }

  @Bean
  @Qualifier("properties")
  public String propertiesUrl() {
    return propertiesUrl;
  }
}
