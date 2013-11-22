package fi.muni.pa165.dto;

import java.io.Serializable;
import java.util.Objects;
import org.joda.time.Duration;

/**
 * 
 * @author Jan Pacner, Oliver Pentek
 */
public class ServiceDto implements Serializable {
  private Long id;
  private String name;
  private Long price;
  private Duration duration;
  
  public ServiceDto(){
  }
  
  public ServiceDto(Long id, String name, Long price, Duration duration) {
    if (name == null) throw new RuntimeException("name == null");
    if (price == null) throw new RuntimeException("price == null");
    if (duration == null) throw new RuntimeException("duration == null");
    this.id = id;
    this.name = name;
    this.price = price;
    this.duration = duration;
  }
  
  public ServiceDto(String name, Long price, Duration duration) {
    if (name == null) throw new RuntimeException("name == null");
    if (price == null) throw new RuntimeException("price == null");
    if (duration == null) throw new RuntimeException("duration == null");
    this.name = name;
    this.price = price;
    this.duration = duration;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    if (id == null) throw new RuntimeException("id == null");
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null) throw new RuntimeException("name == null");
    this.name = name;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    if (price == null) throw new RuntimeException("price == null");
    this.price = price;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    if (duration == null) throw new RuntimeException("duration == null");
    this.duration = duration;
  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServiceDto other = (ServiceDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceDto{" + "id=" + id + ", name=" + name + '}';
    }

}