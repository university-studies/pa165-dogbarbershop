package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.service.DogServiceService;
import fi.muni.pa165.service.ServiceService;
import fi.muni.pa165.web.DogBarberShopApplication;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

/**
 * @author Oliver Pentek
 */
public final class EntrancePage extends TemplatePage {
    private final ServiceService serviceService;
    private final DogService dogService;
    private final DogServiceService dogServiceService;
    
    public EntrancePage() {
        super();
        serviceService = DogBarberShopApplication.get().getServiceService();
        dogService = DogBarberShopApplication.get().getDogService();
        dogServiceService = DogBarberShopApplication.get().getDogServiceService();
        
        this.initComponents();
    }
    
    private void initComponents() {
        final DropDownChoice<DogDto> dogDropDownChoice = new DropDownChoice(ComponentIDs.DOG_DROP_DOWN_CHOICE, new Model<DogDto>(),dogService.getAllDogs());
        final DropDownChoice<ServiceDto> serviceDownChoice = new DropDownChoice(ComponentIDs.SERVICE_DROP_DOWN_CHOICE, new Model<ServiceDto>(), serviceService.getAllServices());
        final Form addDogService = new Form (ComponentIDs.ADD_DOG_SERVICE_FORM) {

            @Override
            protected void onSubmit() {
                super.onSubmit();
                dogServiceService.createDogService(dogDropDownChoice.getModelObject(), serviceDownChoice.getModelObject(), null);
                dogDropDownChoice.setModelObject(null);
                serviceDownChoice.setModelObject(null);
            }
            
        };
        this.add(addDogService);
        addDogService.add(new Button(ComponentIDs.SUBMIT_BUTTON));
        
        
        addDogService.add(dogDropDownChoice);
        addDogService.add(serviceDownChoice);
        
        
    }
    
    private static class ComponentIDs {
        private static final String ADD_DOG_SERVICE_FORM = "addDogServiceForm";
        private static final String DOG_DROP_DOWN_CHOICE = "dogDropDownChoice";
        private static final String SERVICE_DROP_DOWN_CHOICE = "serviceDropDownChoice";
        private static final String SUBMIT_BUTTON = "submitButton";
    }
}