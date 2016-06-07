package br.com.marcelomalcher.vrspotippos.repository;

import java.util.Collection;

import br.com.marcelomalcher.vrspotippos.domain.Province;

public interface ProvinceRepository {

  Province create(Province province);
  Province read(String name);
  Collection<Province> readAll();
  void clear();
}
