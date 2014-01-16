package fi.muni.pa165.web;

import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.service.DogServiceService;
import fi.muni.pa165.service.EmployeeService;
import fi.muni.pa165.service.ServiceService;
import fi.muni.pa165.web.converter.DurationConverter;
import fi.muni.pa165.web.converter.LocalDateConverter;
import fi.muni.pa165.web.pages.DogServicePage;
import fi.muni.pa165.web.pages.SignInPage;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Oliver Pentek, Jan Pacner
 */
public class DogBarberShopApplication extends WebApplication {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DogService dogService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DogServiceService dogServiceService;
    
    @Nonnull
    public DogServiceService getDogServiceService() {
        return dogServiceService;
    }
    
    @Nonnull
    public CustomerService getCustomerService() {
      return customerService;
    }
    
    @Nonnull
    public DogService getDogService() {
      return dogService;
    }

    @Nonnull
    public static DogBarberShopApplication get() {
        return (DogBarberShopApplication) WebApplication.get();
    }
    
    @Nonnull
    public EmployeeService getEmployeeService() {
        return this.employeeService;
    }
    
    @Nonnull
    public ServiceService getServiceService(){
        return this.serviceService;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return SignInPage.class;
    }

    @Override
    protected void init() {
        super.init();
        Validate.notNull(employeeService);
        Validate.notNull(serviceService);
        Validate.notNull(dogService);
        Validate.notNull(serviceService);
        Validate.notNull(customerService);
    }

    @Override
    protected IConverterLocator newConverterLocator() {
        ConverterLocator locator = (ConverterLocator) super.newConverterLocator();
        locator.set(Duration.class, DurationConverter.INSTANCE);
        locator.set(LocalDate.class, LocalDateConverter.INSTANCE);
        return locator;
    }
    
    
}
