package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.service.EmployeeService;
import fi.muni.pa165.web.DogBarberShopApplication;
import fi.muni.pa165.web.SessionAttributes;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author Oliver Pentek
 */
public class SignInPage extends WebPage {

    private final EmployeeService service;

    public SignInPage() {
        super();
        service = DogBarberShopApplication.get().getEmployeeService();
        this.initComponents();
    }

    private void initComponents() {
        final FeedbackPanel feedback = new FeedbackPanel(ComponentIDs.FEEDBACK_PANEL);
        this.add(feedback);
        final RequiredTextField<String> login = new RequiredTextField(ComponentIDs.LOGIN, new Model());
        final PasswordTextField password = new PasswordTextField(ComponentIDs.PASSWORD, new Model());
        password.setRequired(true);
        final Button submit = new Button(ComponentIDs.SUBMIT_BUTTON);
        final Form loginForm = new Form(ComponentIDs.FORM) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                final String inputLogin = login.getValue();
                final String inputPassword = password.getValue();
                

                try {
                    final EmployeeDto employee = service.getEmployeeByLogin(inputLogin);

                    if (!employee.validatePassword(inputPassword)) {
                        info(getLocalizer().getString("SignInPage.password.wrong", this));
                        password.setModelObject("");
                    } else {
                        getSession().setAttribute(SessionAttributes.CURRENT_USER.getText(), employee);
                        setResponsePage(EntrancePage.class);
                    }
                } catch (final EmptyResultDataAccessException ex) {
                    info(getLocalizer().getString("SignInPage.login.wrong", this));
                    login.setModelObject("");
                    password.setModelObject("");
                }

            }
        };
        loginForm.add(login);
        loginForm.add(password);
        loginForm.add(submit);
        loginForm.setDefaultButton(submit);
        this.add(loginForm);
    }

    private static class ComponentIDs {

        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
        private static final String SUBMIT_BUTTON = "submitButton";
        private static final String FORM = "loginForm";
        private static final String FEEDBACK_PANEL = "feedback";
    }
}
