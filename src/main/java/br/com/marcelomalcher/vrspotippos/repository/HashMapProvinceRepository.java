package br.com.marcelomalcher.vrspotippos.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import br.com.marcelomalcher.vrspotippos.domain.Province;

@Repository
public class HashMapProvinceRepository implements ProvinceRepository {

  private final ConcurrentHashMap<String, Province> map = new ConcurrentHashMap<>();

  @Override
  public Province create(Province province) {
    return map.put(handleName(province.getName()), province);
  }

  @Override
  public Province read(String name) {
    return map.get(handleName(name));
  }

  @Override
  public Collection<Province> readAll() {
    return map.values();
  }

  private String handleName(String name) {
    return name.toLowerCase();
  }
}
