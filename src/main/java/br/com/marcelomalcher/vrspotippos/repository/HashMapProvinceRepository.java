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
    String name = handleName(province.getName());
    map.put(name, province);
    return province;
  }

  @Override
  public Province read(String name) {
    return map.get(handleName(name));
  }

  @Override
  public Collection<Province> readAll() {
    return map.values();
  }

  @Override
  public void clear() {
    map.clear();
  }

  private String handleName(String name) {
    return name.toLowerCase();
  }
}
