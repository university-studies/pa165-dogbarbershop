package fi.muni.pa165.dogbarbershop;

import fi.muni.pa165.dao.DogServiceDaoImpl;
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
        DogServiceDaoImpl dao = new DogServiceDaoImpl();
        dao.setEmFromEmf();
        
        System.out.println( "Hello World!" );
    }
}
