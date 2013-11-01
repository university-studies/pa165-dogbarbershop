package fi.muni.pa165.service;

import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.idao.ServiceDao;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author honza
 */
@Service
public class ServiceService {
  @Autowired
  private ServiceDao sd;

  @Transactional
  public ServiceDto addService(ServiceDto s) {
    fi.muni.pa165.entity.Service e = new fi.muni.pa165.entity.Service();
    e.setName(s.getName());
    e.setDuration(s.getDuration());
    e.setPrice(s.getPrice());
    sd.addService(e);
    return s;
  }

  @Transactional
  public void delService(Long id) {
    sd.delService(id);
  }

  @Transactional
  public ServiceDto updateService(ServiceDto s) {
    fi.muni.pa165.entity.Service e = sd.getServiceById(s.getId());
    e.setName(s.getName());
    e.setDuration(s.getDuration());
    e.setPrice(s.getPrice());
    sd.updateService(e);
    return s;
  }

  @Transactional
  public ServiceDto getServiceById(Long id) {
    fi.muni.pa165.entity.Service e = sd.getServiceById(id);
    return new ServiceDto(e.getId(), e.getName(), e.getPrice(), e.getDuration());
  }

  @Transactional
  public List<ServiceDto> getServiceByName(String name) {
    List<fi.muni.pa165.entity.Service> l = sd.getServiceByName(name);
    List<ServiceDto> res = new ArrayList<>();
    for (fi.muni.pa165.entity.Service e : l)
      res.add(new ServiceDto(e.getId(), e.getName(), e.getPrice(), e.getDuration()));
    return res;
  }

  @Transactional
  public List<ServiceDto> getServiceByPrice(Long price) {
    List<fi.muni.pa165.entity.Service> l = sd.getServiceByPrice(price);
    List<ServiceDto> res = new ArrayList<>();
    for (fi.muni.pa165.entity.Service e : l)
      res.add(new ServiceDto(e.getId(), e.getName(), e.getPrice(), e.getDuration()));
    return res;
  }

  @Transactional
  public List<ServiceDto> getServiceByDuration(Duration d) {
    List<fi.muni.pa165.entity.Service> l = sd.getServiceByDuration(d);
    List<ServiceDto> res = new ArrayList<>();
    for (fi.muni.pa165.entity.Service e : l)
      res.add(new ServiceDto(e.getId(), e.getName(), e.getPrice(), e.getDuration()));
    return res;
  }
}