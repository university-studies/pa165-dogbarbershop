package fi.muni.pa165.web.pages;

import fi.muni.pa165.service.EmployeeService;
import org.apache.commons.lang3.Validate;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Oliver Pentek
 */
public class EmployeePage extends WebPage {
    private boolean isUpdateButton;
    private Label whatToDoLabel;
    
    @Autowired
    EmployeeService service;

    public EmployeePage() {
        Validate.notNull(service);
    }
    
    
}
