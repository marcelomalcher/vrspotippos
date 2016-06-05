package br.com.marcelomalcher.vrspotippos.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Province {

  @NotEmpty
  private String name;
  @NotNull
  private Boundaries upperLeft;
  @NotNull
  private Boundaries bottomRight;

  public static class Boundaries {

    private Integer x;
    private Integer y;

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

    @Override
    public String toString() {
      return "{" +
        "x=" + x +
        ", y=" + y +
        '}';
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boundaries getUpperLeft() {
    return upperLeft;
  }

  public void setUpperLeft(Boundaries upperLeft) {
    this.upperLeft = upperLeft;
  }

  public Boundaries getBottomRight() {
    return bottomRight;
  }

  public void setBottomRight(Boundaries bottomRight) {
    this.bottomRight = bottomRight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Province province = (Province) o;

    if (name != null ? !name.equals(province.name) : province.name != null) return false;
    if (upperLeft != null ? !upperLeft.equals(province.upperLeft) : province.upperLeft != null)
      return false;
    return bottomRight != null ? bottomRight.equals(province.bottomRight) : province.bottomRight == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (upperLeft != null ? upperLeft.hashCode() : 0);
    result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Province{" +
      "name='" + name + '\'' +
      ", upperLeft=" + upperLeft +
      ", bottomRight=" + bottomRight +
      '}';
  }
}

