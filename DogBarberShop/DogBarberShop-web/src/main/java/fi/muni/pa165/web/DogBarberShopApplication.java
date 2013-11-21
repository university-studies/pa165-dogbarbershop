package fi.muni.pa165.web;

import fi.muni.pa165.service.DogService;
import fi.muni.pa165.service.EmployeeService;
import fi.muni.pa165.service.ServiceService;
import fi.muni.pa165.web.converter.DurationConverter;
import fi.muni.pa165.web.converter.LocalDateConverter;
import fi.muni.pa165.web.pages.EntrancePage;
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
 * @author Oliver Pentek
 */
public class DogBarberShopApplication extends WebApplication {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DogService dog_service;
    @Autowired
    private ServiceService serviceService;
    
    @Nonnull
    public DogService getDog_service() {
      return dog_service;
    }

    @Nonnull
    public static DogBarberShopApplication get() {
        return (DogBarberShopApplication) WebApplication.get();
    }
    
    @Nonnull
    public EmployeeService getemployeeService() {
        return this.employeeService;
    }
    
    @Nonnull
    public ServiceService getServiceService(){
        return this.serviceService;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return EntrancePage.class;
    }

    @Override
    protected void init() {
        super.init();
        Validate.notNull(employeeService);
        Validate.notNull(serviceService);
    }

    @Override
    protected IConverterLocator newConverterLocator() {
        ConverterLocator locator = (ConverterLocator) super.newConverterLocator();
        locator.set(Duration.class, DurationConverter.INSTANCE);
        locator.set(LocalDate.class, LocalDateConverter.INSTANCE);
        return locator;
    }
    
    
}
