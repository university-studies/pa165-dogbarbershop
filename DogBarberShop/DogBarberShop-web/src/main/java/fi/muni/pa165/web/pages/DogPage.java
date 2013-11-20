package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.joda.time.LocalDate;

/**
 * @author Jan Pacner
 */
public class DogPage extends TemplatePage {
  /* HTML element IDs */
  //private static final String FEEDBACK_PANEL = "feedback";
  private static final String FORM_LISTING    = "form_listing";
  private static final String SPAN_ROW00      = "span_row00";
  private static final String SPAN_NAME       = "span_name";
  private static final String SPAN_BREED      = "span_breed";
  private static final String SPAN_BIRTHDATE  = "span_birthdate";
  private static final String SPAN_OWNER      = "span_owner";
  private static final String BTN_DEL         = "btn_del";
  private static final String BTN_EDIT        = "btn_edit";

  private static final String FORM_DOG_WHOLE  = "form_dog_whole";
  private static final String INPUT_NAME      = "input_name";
  private static final String INPUT_BREED     = "input_breed";
  private static final String INPUT_BIRTHDATE = "input_birthdate";
  private static final String INPUT_OWNER     = "input_owner";
  private static final String BTN_SUBMIT      = "btn_submit";
  //private static final String REFRESH_LINK = "refresh";
  private static final String HEADLINE_ACTION = "headline_action";

  //FIXME find translatable solution
  private static final String LBL_NEW_CUST = "Novy pes";
  private Label actionLbl;

  private boolean isUpdateButton;
  private final DogService serv;

  private static DogDto newDogDto() {
    //FIXME CustomerDao() must already exist!
    return new DogDto("name", "breed", new LocalDate(1999, 5, 26), new CustomerDto());
  }

  public DogPage() {
    serv = DogBarberShopApplication.get().getDog_service();

    CompoundPropertyModel<DogDto> mod = new CompoundPropertyModel<>(newDogDto());
    final Form<DogDto> form_dog_whole = new Form<DogDto>(FORM_DOG_WHOLE, mod) {
      @Override
      protected void onSubmit() {
        super.onSubmit();
        if (DogPage.this.isUpdateButton) {
          //serv.updateDog(this.getModel().getObject());
          serv.updateDog(this.getModelObject());
          this.setModelObject(newDogDto());
          actionLbl.setDefaultModelObject(LBL_NEW_CUST);
          isUpdateButton = false;
        }
        else {
          serv.addDog(this.getModelObject());
          this.setModelObject(newDogDto());
        }
      }
    };

    //add(new FeedbackPanel(FEEDBACK_PANEL));
    // RequiredTextField("ID_from_HTML", mod.bind("class_attr_name")));
    form_dog_whole.add(new RequiredTextField(INPUT_NAME, mod.bind("name")));
    form_dog_whole.add(new RequiredTextField(INPUT_BREED, mod.bind("breed")));
    form_dog_whole.add(new RequiredTextField(INPUT_BIRTHDATE, mod.bind("birthDate")));
    form_dog_whole.add(new RequiredTextField(INPUT_OWNER, mod.bind("owner")));
    form_dog_whole.add(new Button(BTN_SUBMIT));
    add(form_dog_whole);

    final Form form_listing = new Form<DogDto>(
            FORM_LISTING, new Model<DogDto>()) {
      @Override
      protected void onSubmit() {
        super.onSubmit();
        if (DogPage.this.isUpdateButton) {
          form_dog_whole.setModelObject(this.getModelObject());
          actionLbl.setDefaultModelObject("Uprava psa [" +
                  this.getModelObject().getId() + "]");
        }
        else {
          serv.deleteDog(this.getModelObject());
          form_dog_whole.setModelObject(newDogDto());
        }
      }
    };

    form_listing.setOutputMarkupId(true);
    form_listing.add(new ListView<DogDto>(SPAN_ROW00,
            new InnerLoadableCustomerModel()) {
      @Override
      protected void populateItem(ListItem<DogDto> li) {
        final DogDto dto = li.getModelObject();
        li.add(new Label(SPAN_NAME, dto.getName()));
        li.add(new Label(SPAN_BREED, dto.getBreed()));
        li.add(new Label(SPAN_BIRTHDATE, dto.getBirthDate()));
        //FIXME
        //li.add(new Label(SPAN_OWNER, dto.getOwner()));
        li.add(new Label(SPAN_OWNER, "FIXME!!!!!!!"));
        li.add(new Button(BTN_DEL) {
          @Override
          public void onSubmit() {
            super.onSubmit();
            isUpdateButton = false;
            form_listing.setModelObject(dto);
            actionLbl.setDefaultModelObject(LBL_NEW_CUST);
          }
        });
        li.add(new Button(BTN_EDIT) {
          @Override
          public void onSubmit() {
            super.onSubmit();
            isUpdateButton = true;
            form_listing.setModelObject(dto);
          }
        });
      }
    });

    /*
    form_listing.add(new AjaxLink(REFRESH_LINK) {
      @Override
      public void onClick(AjaxRequestTarget target) {
        target.add(tableForm);
        tableForm.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)));
      }
    });
    */

    //form_listing.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)));
    add(form_listing);
    add(actionLbl = new Label(HEADLINE_ACTION, new Model()));
    actionLbl.setDefaultModelObject(LBL_NEW_CUST);
  }

  private class InnerLoadableCustomerModel extends
          LoadableDetachableModel<List<DogDto>> {
    @Override
    protected List<DogDto> load() {
      return DogPage.this.serv.getAllDogs();
    }
  }
}