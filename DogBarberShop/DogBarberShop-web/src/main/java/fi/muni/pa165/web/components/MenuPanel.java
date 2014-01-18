/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.web.components;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.dto.UserRole;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.web.SessionAttributes;
import fi.muni.pa165.web.pages.CustomerPage;
import fi.muni.pa165.web.pages.DogPage;
import fi.muni.pa165.web.pages.EmployeePage;
import fi.muni.pa165.web.pages.ServicePage;
import fi.muni.pa165.web.pages.SignInPage;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class MenuPanel extends Panel {
    
    public MenuPanel(String id) {
        super(id);
        
        BookmarkablePageLink employeePage = new BookmarkablePageLink(ComponentIDs.EMPLOYEES_BUTTON, EmployeePage.class);
        WebMarkupContainer employeeContainer = new WebMarkupContainer(ComponentIDs.EMPLOYEE);
        employeeContainer.add(employeePage);
        this.add(employeeContainer);
        BookmarkablePageLink servicesPage =new BookmarkablePageLink(ComponentIDs.SERVICES_BUTTON, ServicePage.class);
        WebMarkupContainer serviceContainer = new WebMarkupContainer(ComponentIDs.SERVICE);
        serviceContainer.add(servicesPage);
        this.add(serviceContainer);
        BookmarkablePageLink customersPage = new BookmarkablePageLink(ComponentIDs.CUSTOMERS_BUTTON, CustomerPage.class);
        WebMarkupContainer customersContainer = new WebMarkupContainer(ComponentIDs.CUSTOMER);
        customersContainer.add(customersPage);
        this.add(customersContainer);
        BookmarkablePageLink dogsPage = new BookmarkablePageLink(ComponentIDs.DOGS_BUTTON, DogPage.class);
        WebMarkupContainer dogsContainer = new WebMarkupContainer(ComponentIDs.DOG);
        dogsContainer.add(dogsPage);
        add(dogsContainer);
        Link logout = new Link("logoutLink") {
            
            @Override
            public void onClick() {
                getSession().removeAttribute(SessionAttributes.CURRENT_USER.getText());
                setResponsePage(SignInPage.class);
            }
            
        };
        WebMarkupContainer logoutContainer = new WebMarkupContainer(ComponentIDs.LOGOUT);
        logoutContainer.add(logout);
        add(logoutContainer);
        
        final EmployeeDto user = (EmployeeDto) getSession().getAttribute(SessionAttributes.CURRENT_USER.getText());
        if (UserRole.USER.equals(user.getRole())) {
            employeeContainer.setVisible(false);
        } else {
            this.add(new AttributeModifier("style", "background-color:red"));
            employeeContainer.add(new AttributeModifier("class", "menu_button5"));
            serviceContainer.add(new AttributeModifier("class", "menu_button5"));
            customersContainer.add(new AttributeModifier("class", "menu_button5"));
            dogsContainer.add(new AttributeModifier("class", "menu_button5"));
            logoutContainer.add(new AttributeModifier("class", "menu_button5"));
            
            logout.add(new AttributeModifier("class", "menu5"));
            employeePage.add(new AttributeModifier("class", "menu5"));
            customersPage.add(new AttributeModifier("class", "menu5"));
            servicesPage.add(new AttributeModifier("class", "menu5"));
            dogsPage.add(new AttributeModifier("class", "menu5"));
        }
    }
    
    private static class ComponentIDs {
        private static final String EMPLOYEES_BUTTON = "employeesButton";
        private static final String DOGS_BUTTON = "dogsButton";
        private static final String CUSTOMERS_BUTTON = "customersButton";
        private static final String SERVICES_BUTTON = "servicesButton";
        private static final String EMPLOYEE = "employee";
        private static final String SERVICE = "service";
        private static final String CUSTOMER = "customer";
        private static final String DOG = "dog";
        private static final String LOGOUT = "logout";
    }
}
