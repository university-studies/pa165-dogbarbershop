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
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class MenuPanel extends Panel {
    
    public MenuPanel(String id) {
        super(id);
        
        BookmarkablePageLink employeePage = new BookmarkablePageLink(ComponentIDs.EMPLOYEES_BUTTON, EmployeePage.class);
        this.add(employeePage);
        BookmarkablePageLink servicesPage =new BookmarkablePageLink(ComponentIDs.SERVICES_BUTTON, ServicePage.class);
        this.add(servicesPage);
        this.add(new BookmarkablePageLink(ComponentIDs.CUSTOMERS_BUTTON, CustomerPage.class));
        this.add(new BookmarkablePageLink(ComponentIDs.DOGS_BUTTON, DogPage.class));
        // XXX this.add(new BookmarkablePageLink(ComponentIDs.DOG_SERVICE_BUTTON, DogServicePage.class));
        final EmployeeDto user = (EmployeeDto) getSession().getAttribute(SessionAttributes.CURRENT_USER.getText());
        if (UserRole.USER.equals(user.getRole())) {
            employeePage.setVisible(false);
        } else {
            this.add(new AttributeModifier("style", "background-color:red;"));
        }
    }
    
    private static class ComponentIDs {
        private static final String EMPLOYEES_BUTTON = "employeesButton";
        private static final String DOGS_BUTTON = "dogsButton";
        private static final String CUSTOMERS_BUTTON = "customersButton";
        private static final String SERVICES_BUTTON = "servicesButton";
        private static final String DOG_SERVICE_BUTTON = "dogServiceButton";
    }
}
