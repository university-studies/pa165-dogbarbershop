package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.service.DogServiceService;
import fi.muni.pa165.service.ServiceService;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.util.ArrayList;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 * @author Oliver Pentek
 */
public final class DogServicePage extends TemplatePage {

    private final ServiceService serviceService;
    private final DogService dogService;
    private final DogServiceService dogServiceService;
    private final CustomerService customerService;
    private CustomerDto selectedCustomer;

    public DogServicePage() {
        super();
        serviceService = DogBarberShopApplication.get().getServiceService();
        dogService = DogBarberShopApplication.get().getDogService();
        dogServiceService = DogBarberShopApplication.get().getDogServiceService();
        customerService = DogBarberShopApplication.get().getCustomerService();

        this.initComponents();
    }

    private void initComponents() {
         final FeedbackPanel feedback = new FeedbackPanel(ComponentIDs.FEEDBACK_PANEL);
        this.add(feedback);
        
        IChoiceRenderer<CustomerDto> customerRenderer = new ChoiceRenderer<>("surname", "id");
        IChoiceRenderer<DogDto> dogRenderer = new ChoiceRenderer<>("name", "id");
        IChoiceRenderer<ServiceDto> serviceRenderer = new ChoiceRenderer<>("name", "id");

        final DropDownChoice<DogDto> dogDropDownChoice = new DropDownChoice(
                ComponentIDs.DOG_DROP_DOWN_CHOICE, new Model<DogDto>(), new ArrayList(), dogRenderer) {
            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                this.setChoices(selectedCustomer == null ? new ArrayList() : dogService.getDogsByOwner(selectedCustomer));
            }
        };
        final DropDownChoice<CustomerDto> customerDropDownChoice = new DropDownChoice<CustomerDto>(
                ComponentIDs.CUSTOMER_DROP_DOWN_CHOICE, new Model<CustomerDto>(), customerService.getAllCustomers(), customerRenderer) {
            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }

            @Override
            protected void onSelectionChanged(final CustomerDto newSelection) {
                super.onSelectionChanged(newSelection);
                selectedCustomer = newSelection;
            }
        };

        final DropDownChoice<ServiceDto> serviceDownChoice = new DropDownChoice(
                ComponentIDs.SERVICE_DROP_DOWN_CHOICE, new Model<ServiceDto>(), serviceService.getAllServices(), serviceRenderer);

        final Form addDogService = new Form(ComponentIDs.ADD_DOG_SERVICE_FORM) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                dogServiceService.createDogService(dogDropDownChoice.getModelObject(), serviceDownChoice.getModelObject(), null);
                dogDropDownChoice.setModelObject(null);
                serviceDownChoice.setModelObject(null);
                selectedCustomer = null;
            }
        };
        this.add(addDogService);
        addDogService.add(new Button(ComponentIDs.SUBMIT_BUTTON));
        
        dogDropDownChoice.setRequired(true);
        serviceDownChoice.setRequired(true);
        addDogService.add(customerDropDownChoice);
        addDogService.add(dogDropDownChoice);
        addDogService.add(serviceDownChoice);


    }

    private static class ComponentIDs {

        private static final String ADD_DOG_SERVICE_FORM = "addDogServiceForm";
        private static final String CUSTOMER_DROP_DOWN_CHOICE = "customerDropDownChoice";
        private static final String DOG_DROP_DOWN_CHOICE = "dogDropDownChoice";
        private static final String SERVICE_DROP_DOWN_CHOICE = "serviceDropDownChoice";
        private static final String SUBMIT_BUTTON = "submitButton";
        private static final String FEEDBACK_PANEL = "feedback";
    }
}