package fi.muni.pa165.dto;

import com.google.common.eventbus.AllowConcurrentEvents;
import org.joda.time.Duration;

/**
 *  @TODO doplnit vsade validaciu aby sme nemali v DTO null hodnoty
 * 
 * @author Oliver Pentek
 */
public class ServiceDto {
    private Long id;
  private String name;
  private Long price;
  private Duration duration;

    public ServiceDto(Long id, String name, Long price, Duration duration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
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

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
  
  
}
