package fi.muni.pa165.dogbarbershop;

import fi.muni.pa165.entity.*;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Dog dog = new Dog("havo", "pudel", Date.valueOf("2013-10-07"));
        Service service = new Service();
        service.setName("strihanie");
        em.persist(service);
        
        DogService dogService = new DogService();
        dogService.setDog(dog);
        dogService.setService(service);
        dogService.setServiceDate(Date.valueOf("2013-10-08"));
        dogService.setServedBy(new Long(1));
        
        dog.getDogServices().add(dogService);
        
        em.persist(dog);
        
        em.getTransaction().commit();
        
        System.out.println( "Hello World!" );
    }
}
