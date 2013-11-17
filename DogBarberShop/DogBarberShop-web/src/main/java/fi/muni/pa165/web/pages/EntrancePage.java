package fi.muni.pa165.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Oliver Pentek, Jan Pacner
 */
public class EntrancePage extends WebPage {
    public EntrancePage() {
        super();
        this.initComponents();
    }
    
    private void initComponents() {
        this.add(new Link(ComponentIDs.EMPLOYEE_LINK) {
            @Override
            public void onClick() {
                this.getRequestCycle().setResponsePage(EmployeePage.class);
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
    }
    
    private static class ComponentIDs {
        private static final String EMPLOYEE_LINK = "employeeLink";
        private static final String A_DOG = "a_dog";
        private static final String A_CUSTOMER = "a_customer";
    }
}