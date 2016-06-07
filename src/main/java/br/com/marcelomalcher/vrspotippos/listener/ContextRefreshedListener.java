package br.com.marcelomalcher.vrspotippos.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

import br.com.marcelomalcher.vrspotippos.service.loader.PropertiesLoaderService;
import br.com.marcelomalcher.vrspotippos.service.loader.ProvincesLoaderService;

/**
 * Context listener
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  ProvincesLoaderService provinceLoaderService;
  @Autowired
  PropertiesLoaderService propertiesLoaderService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    loadProvinces();
    loadProperties();
  }

  private void loadProvinces() {
    logger.debug("Retrieve provinces...");
    try {
      provinceLoaderService.loadProvinces();
    } catch (IOException e) {
      logger.debug("Error while loading provinces {}", e.getMessage());
      logger.error("Error", e);
    }
  }

  private void loadProperties() {
    logger.debug("Retrieve properties...");
    try {
      propertiesLoaderService.loadProperties();
    } catch (IOException e) {
      logger.debug("Error while loading properties {}", e.getMessage());
      logger.error("Error", e);
    }
  }
}