package br.com.marcelomalcher.vrspotippos.domain.search;

import com.google.common.collect.Lists;

import org.springframework.util.StringUtils;

import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Property;

public class SearchPropertiesResult {

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
      if (!StringUtils.isEmpty(properties)) {
        this.properties.addAll(properties);
      }
      return this;
    }

    public SearchPropertiesResult build() {
      SearchPropertiesResult result = new SearchPropertiesResult();
      result.foundProperties = this.properties.size();
      result.properties = this.properties;
      return result;
    }
  }
}
