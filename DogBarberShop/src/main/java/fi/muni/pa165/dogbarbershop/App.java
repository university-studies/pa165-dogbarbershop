package fi.muni.pa165.dogbarbershop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fi.muni.pa165.service.ServiceClass;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/context.xml");
        
        ServiceClass service = context.getBean(ServiceClass.class);
        
        service.isInjected();
        service.isInjectedAttributesInjected();
    }
}
