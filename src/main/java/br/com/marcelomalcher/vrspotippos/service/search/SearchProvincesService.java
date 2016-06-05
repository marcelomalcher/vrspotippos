package br.com.marcelomalcher.vrspotippos.service.search;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.marcelomalcher.vrspotippos.domain.Province;

@Service
public class SearchProvincesService {

  public Collection<Province> filterProvinces(Collection<Province> provinces,
                                              Integer x, Integer y) {
    return provinces
      .stream()
      .filter(
        p -> p != null &&
          p.getBottomRight().getX() >= x && p.getUpperLeft().getX() <= x &&
          p.getUpperLeft().getY() >= y && p.getBottomRight().getY() <= y)
      .collect(Collectors.toList());
  }
}
