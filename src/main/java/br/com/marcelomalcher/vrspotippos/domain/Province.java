package br.com.marcelomalcher.vrspotippos.domain;

public class Province {

  private String name;
  private Boundaries upperLeft;
  private Boundaries bottomRight;

  public static class Boundaries {
    Integer x;
    Integer y;
  }
}
