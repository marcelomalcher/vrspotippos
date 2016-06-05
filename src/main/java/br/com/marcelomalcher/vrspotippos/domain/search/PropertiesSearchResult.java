package br.com.marcelomalcher.vrspotippos.domain.search;

import com.google.common.collect.Lists;

import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Property;

public class PropertiesSearchResult {

  private Integer foundProperties;
  private Collection<Property> properties;

  public Integer getFoundProperties() {
    return foundProperties;
  }

  public Collection<Property> getProperties() {
    return properties;
  }

  public static class Builder {

    private final Collection<Property> properties = Lists.newArrayList();

    public Builder withProperties(Collection<Property> properties) {
      if (properties != null) {
        this.properties.addAll(properties);
      }
      return this;
    }

    public PropertiesSearchResult build() {
      PropertiesSearchResult result = new PropertiesSearchResult();
      result.foundProperties = this.properties.size();
      result.properties = this.properties;
      return result;
    }
  }
}
