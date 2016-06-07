package br.com.marcelomalcher.vrspotippos.service.filter;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.marcelomalcher.vrspotippos.domain.Province;
import br.com.marcelomalcher.vrspotippos.repository.ProvinceRepository;

@Service
public class FilterProvincesService {

  @Autowired
  ProvinceRepository repository;

  public Collection<Province> filterProvinces(Integer x, Integer y) {
    Collection<Province> provinces = repository.readAll();
    if (!StringUtils.isEmpty(provinces)) {
      return provinces
        .stream()
        .filter(
          p -> p != null &&
            p.getBottomRight().getX() >= x && p.getUpperLeft().getX() <= x &&
            p.getUpperLeft().getY() >= y && p.getBottomRight().getY() <= y)
        .collect(Collectors.toList());
    }
    return Lists.newArrayList();
  }
}
