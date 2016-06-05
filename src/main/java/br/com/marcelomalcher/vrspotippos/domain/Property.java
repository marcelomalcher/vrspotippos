package br.com.marcelomalcher.vrspotippos.domain;

import java.util.List;

public class Property {

  private String id;
  private String title;
  private Long price;
  private String description;
  private Integer x;
  private Integer y;
  private Integer beds;
  private Integer baths;
  private List<String> provices;
  private Integer squareMeters;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getBeds() {
    return beds;
  }

  public void setBeds(Integer beds) {
    this.beds = beds;
  }

  public Integer getBaths() {
    return baths;
  }

  public void setBaths(Integer baths) {
    this.baths = baths;
  }

  public List<String> getProvices() {
    return provices;
  }

  public void setProvices(List<String> provices) {
    this.provices = provices;
  }

  public Integer getSquareMeters() {
    return squareMeters;
  }

  public void setSquareMeters(Integer squareMeters) {
    this.squareMeters = squareMeters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Property property = (Property) o;

    if (id != null ? !id.equals(property.id) : property.id != null) return false;
    if (title != null ? !title.equals(property.title) : property.title != null) return false;
    if (price != null ? !price.equals(property.price) : property.price != null) return false;
    if (description != null ? !description.equals(property.description) : property.description != null)
      return false;
    if (x != null ? !x.equals(property.x) : property.x != null) return false;
    if (y != null ? !y.equals(property.y) : property.y != null) return false;
    if (beds != null ? !beds.equals(property.beds) : property.beds != null) return false;
    if (baths != null ? !baths.equals(property.baths) : property.baths != null) return false;
    if (provices != null ? !provices.equals(property.provices) : property.provices != null)
      return false;
    return squareMeters != null ? squareMeters.equals(property.squareMeters) : property.squareMeters == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (x != null ? x.hashCode() : 0);
    result = 31 * result + (y != null ? y.hashCode() : 0);
    result = 31 * result + (beds != null ? beds.hashCode() : 0);
    result = 31 * result + (baths != null ? baths.hashCode() : 0);
    result = 31 * result + (provices != null ? provices.hashCode() : 0);
    result = 31 * result + (squareMeters != null ? squareMeters.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Property{" +
      "id='" + id + '\'' +
      ", title='" + title + '\'' +
      ", price=" + price +
      ", description='" + description + '\'' +
      ", x=" + x +
      ", y=" + y +
      ", beds=" + beds +
      ", baths=" + baths +
      ", provices=" + provices +
      ", squareMeters=" + squareMeters +
      '}';
  }
}
