package fi.muni.pa165.web;

import fi.muni.pa165.service.EmployeeService;
import fi.muni.pa165.web.pages.EntrancePage;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Oliver Pentek
 */
public class DogBarberShopApplication extends WebApplication {

    @Autowired
    private EmployeeService employeeService;

    @Nonnull
    public static DogBarberShopApplication get() {
        return (DogBarberShopApplication) WebApplication.get();
    }
    
    @Nonnull
    public EmployeeService getemployeeService() {
        return this.employeeService;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return EntrancePage.class;
    }

    @Override
    protected void init() {
        super.init();
        Validate.notNull(employeeService);
    }
}
