package fi.muni.pa165.service;

import fi.muni.pa165.service.impl.ServiceServiceImpl;
import fi.muni.pa165.dao.impl.ServiceDaoImpl;
import fi.muni.pa165.dto.ServiceDto;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author honza
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class ServiceServiceTest {
  @Autowired
  @InjectMocks  // inject all mocks into the following class
  private ServiceServiceImpl s;

  // use "Mock DAO objects" as stated in checkpoint 2 spec.
  @Mock(name = "sd")
  private ServiceDaoImpl sd;

  @Before
  public void setUp() {
    if (sd == null) throw new RuntimeException("[bean] sd == null");
    if (s == null) throw new RuntimeException("[bean] s == null");
    // FIXME mock does not report failed injection (http://docs.mockito.googlecode.com/hg/latest/org/mockito/InjectMocks.html)
    //   => check it manually
    //???
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void addServiceTest() {
    // act [call method which will in turn call the below mocked method]
    ServiceDto dto = new ServiceDto(0L, "cleaning", 50L, new Duration(999));
    s.addService(dto);

    ArgumentCaptor<fi.muni.pa165.entity.Service> cap =
            ArgumentCaptor.forClass(fi.muni.pa165.entity.Service.class);
    Mockito.verify(sd).addService(cap.capture());

    // check [if the subsequent calls were proceeded]
    // NOTE do not check getId() because it is automatically assigned
    //   first after calling persist()
    assertEquals(dto.getName(), cap.getValue().getName());
    assertEquals(dto.getPrice(), cap.getValue().getPrice());
    assertEquals(dto.getDuration(), cap.getValue().getDuration());
  }

  @Test
  public void delServiceTest() {
    s.delService(5L);

    ArgumentCaptor<Long> cap = ArgumentCaptor.forClass(Long.class);
    Mockito.verify(sd).delService(cap.capture());

    assertEquals(5L, (long)cap.getValue());
  }

  @Test
  public void updateServiceTest() {
    fi.muni.pa165.entity.Service e = new fi.muni.pa165.entity.Service();
    e.setId(5L);
    Mockito.stub(sd.getServiceById(e.getId())).toReturn(e);

    ServiceDto dto = new ServiceDto(e.getId(), "cleaning", 88L, new Duration(999));
    s.updateService(dto);

    ArgumentCaptor<Long> cap0 = ArgumentCaptor.forClass(Long.class);
    Mockito.verify(sd).getServiceById(cap0.capture());
    ArgumentCaptor<fi.muni.pa165.entity.Service> cap1 =
            ArgumentCaptor.forClass(fi.muni.pa165.entity.Service.class);
    Mockito.verify(sd).updateService(cap1.capture());

    assertEquals(dto.getId(), cap0.getValue());
    assertEquals(dto.getId(), cap1.getValue().getId());
    assertEquals(dto.getName(), cap1.getValue().getName());
    assertEquals(dto.getPrice(), cap1.getValue().getPrice());
    assertEquals(dto.getDuration(), cap1.getValue().getDuration());
  }

  @Test
  public void getServiceByIdTest() {
    fi.muni.pa165.entity.Service e = new fi.muni.pa165.entity.Service();
    e.setId(5L);
    e.setName("abc");
    e.setPrice(23435L);
    e.setDuration(Duration.ZERO);
    Mockito.stub(sd.getServiceById(e.getId())).toReturn(e);

    ServiceDto dto = s.getServiceById(e.getId());

    ArgumentCaptor<Long> cap0 = ArgumentCaptor.forClass(Long.class);
    Mockito.verify(sd).getServiceById(cap0.capture());

    assertEquals(dto.getId(), e.getId());
  }

  @Test
  public void getServiceByNameTest() {
    final String NAME = "abc";

    List<fi.muni.pa165.entity.Service> l = new ArrayList<>();
    fi.muni.pa165.entity.Service e = new fi.muni.pa165.entity.Service();
    e.setId(5L);
    e.setName(NAME);
    e.setPrice(356L);
    e.setDuration(Duration.standardMinutes(43));
    l.add(e);
    fi.muni.pa165.entity.Service ee = new fi.muni.pa165.entity.Service();
    ee.setId(6L);
    ee.setName(NAME);
    ee.setPrice(32L);
    ee.setDuration(Duration.standardHours(3));
    l.add(ee);
    Mockito.stub(sd.getServiceByName(NAME)).toReturn(l);

    List<ServiceDto> res = s.getServiceByName(NAME);

    assertEquals(res.size(), 2);
    assertEquals((long)res.get(0).getId(), 5L);
    assertEquals((long)res.get(1).getId(), 6L);
  }

  @Test
  public void getServiceByPrice() {
    final long PRICE = 32;

    List<fi.muni.pa165.entity.Service> l = new ArrayList<>();
    fi.muni.pa165.entity.Service e = new fi.muni.pa165.entity.Service();
    e.setId(5L);
    e.setName("xyz");
    e.setPrice(PRICE);
    e.setDuration(Duration.standardMinutes(43));
    l.add(e);
    fi.muni.pa165.entity.Service ee = new fi.muni.pa165.entity.Service();
    ee.setId(6L);
    ee.setName("abc");
    ee.setPrice(PRICE);
    ee.setDuration(Duration.standardHours(3));
    l.add(ee);
    Mockito.stub(sd.getServiceByPrice(PRICE)).toReturn(l);

    List<ServiceDto> res = s.getServiceByPrice(PRICE);

    assertEquals(res.size(), 2);
    assertEquals((long)res.get(0).getId(), 5L);
    assertEquals((long)res.get(1).getId(), 6L);
  }

  @Test
  public void getServiceByDurationTest() {
    final Duration DURATION = Duration.standardMinutes(33);

    List<fi.muni.pa165.entity.Service> l = new ArrayList<>();
    fi.muni.pa165.entity.Service e = new fi.muni.pa165.entity.Service();
    e.setId(5L);
    e.setName("xyz");
    e.setPrice(356L);
    e.setDuration(DURATION);
    l.add(e);
    fi.muni.pa165.entity.Service ee = new fi.muni.pa165.entity.Service();
    ee.setId(6L);
    ee.setName("abc");
    ee.setPrice(32L);
    ee.setDuration(DURATION);
    l.add(ee);
    Mockito.stub(sd.getServiceByDuration(DURATION)).toReturn(l);

    List<ServiceDto> res = s.getServiceByDuration(DURATION);

    assertEquals(res.size(), 2);
    assertEquals((long)res.get(0).getId(), 5L);
    assertEquals((long)res.get(1).getId(), 6L);
  }
}