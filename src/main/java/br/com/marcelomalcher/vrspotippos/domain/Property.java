package br.com.marcelomalcher.vrspotippos.domain;

import org.hibernate.validator.constraints.Range;

import java.util.Collection;

public class Property {

  private Integer id;
  private String title;
  private Long price;
  private String description;
  @Range(min = 0, max = 1400, message = "X não é válido e deve obdecer o limite: 0 <= x <= 1400")
  private Integer x;
  @Range(min = 0, max = 1000, message = "Y não é válido e deve obdecer o limite: 0 <= y <= 1000")
  private Integer y;
  @Range(min = 1, max = 5, message = "Um imóvel em Spotippos deve ter no máximo 5 quartos (beds)," +
    " e no mínimo 1")
  private Integer beds;
  @Range(min = 1, max = 4, message = "Um imóvel em Spotippos deve ter no máximo 4 banheiros " +
    "(baths) e no mínimo 1")
  private Integer baths;
  private Collection<String> provinces;
  @Range(min = 20, max = 240, message = "Um imóvel em Spotippos deve ter no máximo 240 metros " +
    "quadrados e no mínimo 20")
  private Integer squareMeters;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Collection<String> getProvinces() {
    return provinces;
  }

  public void setProvinces(Collection<String> provinces) {
    this.provinces = provinces;
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
    if (provinces != null ? !provinces.equals(property.provinces) : property.provinces != null)
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
    result = 31 * result + (provinces != null ? provinces.hashCode() : 0);
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
      ", provinces=" + provinces +
      ", squareMeters=" + squareMeters +
      '}';
  }
}
