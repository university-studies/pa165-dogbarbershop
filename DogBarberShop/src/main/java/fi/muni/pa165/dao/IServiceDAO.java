/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Service;
import org.joda.time.Duration;

/**
 * @author Honza
 */
public interface IServiceDAO {
  public Service getServiceById(Long id);
  public Service getServiceByName(String name);
  public Service getServiceByPrice(Long price);
  public Service getServiceByDuration(Duration duration);
}