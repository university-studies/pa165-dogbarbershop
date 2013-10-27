package fi.muni.pa165.dogbarbershop;

import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.service.CustomerService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fi.muni.pa165.service.ServiceClass;

import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.idao.CustomerDao;

public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/context.xml");
        
//        ServiceClass service = context.getBean(ServiceClass.class);
//        
//        service.isInjected();
//        service.isInjectedAttributesInjected();
        
        CustomerService customerService = (CustomerService)context.getBean("customerService");
        
        if (customerService == null){
            System.out.println("ZLE je, customer service je null!!!!");
        }
        else {
            System.out.println("Customer service nie je null. Huraaaa");
        }
        
        if (customerService.getCustomerDao() == null) {
            System.out.println("CustomerDao v CustomerService je null!");
        }
        else {
            System.out.println("VSetko v poriadku");
        }
        
        CustomerDao customerDao = customerService.getCustomerDao();
        
        if (((CustomerDaoImpl)customerDao).getEm() == null) {
            System.out.println("Zle je. Entity manager je null!");
        }
        else {
            System.out.println("Entity manager nie je null. Hurraaaaa.");
        }
        
//        Customer customer = new Customer();
//        customer.setName("Martin");
//        customer.setSurname("Sakac");
//        
//        Customer cust2 = customerService.addCustomer(customer);
        
    }
}
