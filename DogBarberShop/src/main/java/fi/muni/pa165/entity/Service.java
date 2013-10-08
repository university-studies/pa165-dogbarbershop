package fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.joda.time.Duration;

/**
 * @author Honza
 */
@Entity
public class Service implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "SERVICE_ID")
  private Long id;
  
  private String name;
  private Long price;  // CZK
  private Duration duration;  // ms
  
  @ManyToMany(mappedBy = "services")
  private List<Employee> employees = new ArrayList<>();
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.service")
  private List<DogService> dogServices = new ArrayList<>();

  public Service() {
  }

  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Long getPrice() {
    return price;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
    final Service other = (Service) obj;
    if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }
}
