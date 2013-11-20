package fi.muni.pa165.web.pages;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;

/**
 * @author Oliver Pentek, Jan Pacner, martin
 */
public class EntrancePage extends TemplatePage {
    public EntrancePage() {
        super();
        this.initComponents();
    }
    
    private void initComponents() {
        
        this.add(new Link<EmployeePage>(ComponentIDs.EMPLOYEE_LINK) {
            @Override
            public void onClick() {
                this.setResponsePage(EmployeePage.class);
            }
        });
        this.add(new Link(ComponentIDs.A_DOG) {
            @Override
            public void onClick() {
                this.getRequestCycle().setResponsePage(DogPage.class);
            }
        });
        this.add(new Link(ComponentIDs.A_CUSTOMER) {
            @Override
            public void onClick() {
                this.getRequestCycle().setResponsePage(CustomerPage.class);
            }
        });
        this.add(new Link(ComponentIDs.SERVICE_LINK) { 
            @Override
            public void onClick() {
                this.getRequestCycle().setResponsePage(ServicePage.class);
            }
        });
    }
    
    private static class ComponentIDs {
        private static final String EMPLOYEE_LINK = "employeeLink";
        private static final String A_DOG = "a_dog";
        private static final String A_CUSTOMER = "a_customer";
        private static final String SERVICE_LINK = "serviceLink";
    }
}