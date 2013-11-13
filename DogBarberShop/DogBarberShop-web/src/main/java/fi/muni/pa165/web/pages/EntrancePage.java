package fi.muni.pa165.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 *
 * @author Oliver Pentek
 */
public class EntrancePage extends WebPage {
    
    public EntrancePage() {
        super();
        this.initComponents();
    }
    
    private void initComponents() {
        Link employeeLink = new Link(ComponentIDs.EMPLOYEE_LINK) {
            @Override
            public void onClick() {
                this.getRequestCycle().setResponsePage(EmployeePage.class);
            }
        };
        
        this.add(employeeLink);
              
    }
    /**
     *  Zoznam komponent vramci tejto
     */
    private static class ComponentIDs {
        private static final String EMPLOYEE_LINK = "employeeLink";
    }
}
