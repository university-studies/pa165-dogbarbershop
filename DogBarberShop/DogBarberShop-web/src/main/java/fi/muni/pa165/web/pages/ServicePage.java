package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.service.ServiceService;
import fi.muni.pa165.web.DogBarberShopApplication;
import fi.muni.pa165.web.converter.DurationConverter;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.joda.time.Duration;

/**
 *
 * @author martin
 */

public class ServicePage extends TemplatePage {
    private final int TABLE_RELOAD_INTERVAL = 5;
    private boolean isUpdateButton;
    private Label whatToDoLabel;
    private final ServiceService serviceService;


    public ServicePage() {
        serviceService = DogBarberShopApplication.get().getServiceService();
        this.initComponents();
    }
    
    private void initComponents() {
        final FeedbackPanel feedback = new FeedbackPanel(ServicePage.ComponentIDs.FEEDBACK_PANEL);
        add(feedback);
        final RequiredTextField name = new RequiredTextField<>(ServicePage.ComponentIDs.NAME);
        final RequiredTextField price = new RequiredTextField<>(ServicePage.ComponentIDs.PRICE);
        final RequiredTextField duration = new RequiredTextField<>(ServicePage.ComponentIDs.DURATION);
        
        final Form<ServiceDto> editableForm = new Form<ServiceDto>(ServicePage.ComponentIDs.ADD_EDIT_FORM, 
                new CompoundPropertyModel<>(new ServiceDto())) {
            @Override
            protected void onSubmit() {
                IModel<ServiceDto> model = this.getModel();
                ServiceDto service = model.getObject();
                if (!ServicePage.this.isUpdateButton) {                        //adding
                    serviceService.addService(service);
                    this.setModel(new CompoundPropertyModel<>(new ServiceDto()));
                } else {                                                        //updating
                    serviceService.updateService(service);
                    this.setModelObject(new ServiceDto());
                    this.getModelObject();
                    setNewCustomerLabel();
                    isUpdateButton = false;
                }
            }
        };

        Button submit = new Button(ServicePage.ComponentIDs.SUBMIT_BUTTON);
        editableForm.add(submit);
        editableForm.add(name);
        editableForm.add(price);
        editableForm.add(duration);
        add(editableForm);
        
        final Form tableForm = new Form<ServiceDto>(ServicePage.ComponentIDs.TABLE_FORM, new Model<ServiceDto>()) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                ServiceDto service = this.getModelObject();
                if (!ServicePage.this.isUpdateButton) {                         //delete
                    serviceService.delService(service);
                    editableForm.setModelObject(new ServiceDto());
                } else {                                                        //update
                    editableForm.setModelObject(service);
                    setUpdateCustomerlabel();
                }
            }
        };

        final IModel<? extends List<? extends ServiceDto>> serviceModel = new ServicePage.InnerLoadableCustomerModel();
        final ListView<ServiceDto> listView = new ListView<ServiceDto>(ServicePage.ComponentIDs.SERVICES_LIST, serviceModel) {
            @Override
            protected void populateItem(ListItem<ServiceDto> li) {
                final ServiceDto service = li.getModelObject();
                li.add(new Label(ServicePage.ComponentIDs.NAME_LIST_ITEM, service.getName()));
                li.add(new Label(ServicePage.ComponentIDs.PRICE_LIST_ITEM, service.getPrice()));
                li.add(new Label(ServicePage.ComponentIDs.DURATION_LIST_ITEM, service.getDuration()));
                li.add(new Button(ServicePage.ComponentIDs.DELETE_BUTTON) {
                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                        isUpdateButton = false;
                        tableForm.setModelObject(service);
                        setNewCustomerLabel();
                    }
                });

                li.add(new Button(ServicePage.ComponentIDs.EDIT_BUTTON) {
                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                        isUpdateButton = true;
                        tableForm.setModelObject(service);
                    }
                });
            }
        };
        

        
        tableForm.setOutputMarkupId(true);
        tableForm.add(listView);
 //       tableForm.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(TABLE_RELOAD_INTERVAL)));
        add(tableForm);
        add(whatToDoLabel = new Label(ServicePage.ComponentIDs.ADD_EDIT_LABEL, new Model()));
        setNewCustomerLabel();
        
    }
    
    private void setNewCustomerLabel() {
        whatToDoLabel.setDefaultModelObject(this.getLocalizer().getString("ServicePage.label.newService", this));
    }

    private void setUpdateCustomerlabel() {
        whatToDoLabel.setDefaultModelObject(this.getLocalizer().getString("ServicePage.label.editService", this));
    }

    private class InnerLoadableCustomerModel extends LoadableDetachableModel<List<ServiceDto>> {

        @Override
        protected List<ServiceDto> load() {
            return ServicePage.this.serviceService.getAllServices();
        }
    }
    
    /**
     *  Zoznam komponent vramci tejto
     */
    private static class ComponentIDs {
        private static final String FEEDBACK_PANEL = "feedback";
        private static final String NAME = "name";
        private static final String PRICE = "price";
        private static final String DURATION = "duration";
        private static final String ADD_EDIT_FORM = "addServiceForm";
        private static final String SUBMIT_BUTTON = "submitButton";
        private static final String EDIT_BUTTON = "editButton";
        private static final String DELETE_BUTTON = "deleteButton";
        private static final String TABLE_FORM = "servicesTable";
        private static final String SERVICES_LIST = "services";
        private static final String NAME_LIST_ITEM = "listName";
        private static final String PRICE_LIST_ITEM = "listPrice";
        private static final String DURATION_LIST_ITEM = "listDuration";
        private static final String REFRESH_LINK = "refresh";
        private static final String ADD_EDIT_LABEL = "whatToDo";
    }
}
