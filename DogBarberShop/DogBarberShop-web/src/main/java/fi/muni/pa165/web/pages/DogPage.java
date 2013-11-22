package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.web.DogBarberShopApplication;
import fi.muni.pa165.web.validator.LocalDateValidator;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.LocalDate;



/**
 * @author Jan Pacner
 */
public class DogPage extends TemplatePage {
  /* HTML element IDs */
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
  private static final String SELECT_OWNER    = "select_owner";
  private static final String BTN_SUBMIT      = "btn_submit";
  private static final String H2_ACTION       = "h2_action";

  private final Label action;
  private final Form form_dog_whole;
  private final Form form_listing;

  private enum State {NEW_DOG, EDIT_DOG};
  private State st = State.NEW_DOG;
  private final DogService dogser;
  private final CustomerService custser;
  private DropDownChoice select_owner;
  private RequiredTextField input_name;
  private RequiredTextField input_breed;
  private RequiredTextField input_birthdate;
  //private DateTextField input_birthdate;

  private static DogDto newDogDto() {
    return new DogDto("Alik", "yorkshire", new LocalDate(2013, 1, 1), null);
  }

  public DogPage() {
    dogser = DogBarberShopApplication.get().getDogService();
    custser = DogBarberShopApplication.get().getCustomerService();
    //to_edit = newDogDto();

    add(form_listing = new Form<DogDto>(FORM_LISTING, new Model<DogDto>()) {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true);
        add(new ListView<DogDto>(SPAN_ROW00,
                new LoadableDetachableModel<List<DogDto>>() {
                  @Override
                  protected List<DogDto> load() {
                    return DogPage.this.dogser.getAllDogs();
                  }
                })
        {
          @Override
          protected void populateItem(final ListItem<DogDto> li) {
            //final DogDto dto = li.getModelObject();
            li.add(new Label(SPAN_NAME, li.getModelObject().getName()));
            li.add(new Label(SPAN_BREED, li.getModelObject().getBreed()));
            li.add(new Label(SPAN_BIRTHDATE, li.getModelObject().getBirthDate()));
            li.add(new Label(SPAN_OWNER,
                    li.getModelObject().getOwner().getSurname() + " " +
                    li.getModelObject().getOwner().getName()));

            li.add(new Button(BTN_DEL) {
              @Override
              public void onSubmit() {
                super.onSubmit();
                //if (li.getModelObject() == to_edit) {
                //  st = State.NEW_DOG;
                //  action.setDefaultModelObject("ADD NEW DOG [btn_del]");
                //  form_listing = newDogDto();
                //  to_edit = newDogDto();
                //  //form_dog_whole.setModelObject(to_edit);
                //  //choices.setModel(new PropertyModel<CustomerDto>(to_edit, "owner"));
                //}
                dogser.deleteDog(li.getModelObject());
              }
            });

            li.add(new Button(BTN_EDIT) {
              @Override
              public void onSubmit() {
                super.onSubmit();
                form_dog_whole.setModelObject(li.getModelObject());
                //to_edit = dto;
                //form_dog_whole.setModelObject(to_edit);
                //choices.setModel(new PropertyModel<CustomerDto>(to_edit, "owner"));
                st = State.EDIT_DOG;
                action.setDefaultModelObject(DogPage.this.getLocalizer()
                        .getString("DogPage.action.edit_dog", DogPage.this));
              }
            });
          }
        });
      }
    });

    add(action = new Label(H2_ACTION, new Model()));
    //add(AttributeModifier.replace("key", "DogPage.action.new_dog"));

    add(form_dog_whole = new Form<DogDto>(FORM_DOG_WHOLE, new Model()) {
      @Override
      protected void onModelChanged() {
        if (input_name != null) input_name.setModelObject(
                form_dog_whole.getModelObject());
        if (input_breed != null) input_breed.setModelObject(
                form_dog_whole.getModelObject());
        if (input_birthdate != null) input_birthdate.setModelObject(
                form_dog_whole.getModelObject());
        if (select_owner != null) select_owner.setModelObject(form_dog_whole.getModelObject());
      }

      @Override
      protected void onInitialize() {
        super.onInitialize();

        if (form_dog_whole.getModelObject() == null)
          form_dog_whole.setModelObject(newDogDto());

        add(input_name = new RequiredTextField(INPUT_NAME,
                new PropertyModel(form_dog_whole.getModelObject(), "name")));
        add(input_breed = new RequiredTextField(INPUT_BREED,
                new PropertyModel(form_dog_whole.getModelObject(), "breed")));
        add(input_birthdate = new RequiredTextField(INPUT_BIRTHDATE,
                new PropertyModel(form_dog_whole.getModelObject(), "birthDate")));
        //add(input_birthdate = new DateTextField(INPUT_BIRTHDATE,
        //        new PropertyModel(form_dog_whole.getModelObject(), "birthDate"),
        //        new StyleDateConverter(true)));
                //(DateConverter)new fi.muni.pa165.web.converter.LocalDateConverter()));
                //(DateConverter)new LocalDateConverter()));
        //add(input_birthdate = new RequiredTextField(INPUT_BIRTHDATE, DateTime.class));
        //input_birthdate.setRequired(true);
        input_birthdate.add(new LocalDateValidator());
        add(select_owner = new DropDownChoice(
                SELECT_OWNER,
                new PropertyModel(form_dog_whole.getModelObject(), "owner"),
                new LoadableDetachableModel<List<CustomerDto>>() {
                  @Override
                  protected List<CustomerDto> load() {
                    return custser.getAllCustomers();
                  }
                }));
        add(new Button(BTN_SUBMIT) {
          @Override
          public void onSubmit() {
            super.onSubmit();

            if (((DogDto)form_dog_whole.getModelObject()).getOwner() == null) {
              action.setDefaultModelObject(DogPage.this.getLocalizer()
                      .getString("DogPage.action.no_owner", DogPage.this));
              return;
            }

            switch (st) {
              case EDIT_DOG:
                st = State.NEW_DOG;
                //action.setDefaultModelObject("ADD NEW DOG [edit_dog -> new_dog]");
                action.setDefaultModelObject(DogPage.this.getLocalizer()
                        .getString("DogPage.action.new_dog", DogPage.this));
                //form_dog_whole.setModelObject(newDogDto());
                dogser.updateDog((DogDto)form_dog_whole.getModelObject());
                form_dog_whole.setModelObject(newDogDto());
                //to_edit = newDogDto();
                //to_edit = (DogDto)form_dog_whole.getModelObject();
                //choices.setModel(new PropertyModel<CustomerDto>(to_edit, "owner"));
                //action.setDefaultModelObject(LBL_NEW_CUST);
                break;
              case NEW_DOG:
                //form_dog_whole.setModelObject(newDogDto());
                //action.setDefaultModelObject("ADD NEW DOG [new_dog -> new_dog]");
                action.setDefaultModelObject(DogPage.this.getLocalizer()
                        .getString("DogPage.action.new_dog", DogPage.this));
                dogser.addDog((DogDto)form_dog_whole.getModelObject());
                form_dog_whole.setModelObject(newDogDto());
                //to_edit = newDogDto();
                //to_edit = (DogDto)form_dog_whole.getModelObject();
                //choices.setModel(new PropertyModel<CustomerDto>(to_edit, "owner"));
                break;
              default:
            }
          }
        });
      }

      // called once per request on components before they are about to be rendered
      @Override
      protected void onConfigure() {
        super.onConfigure();
        //DogBarberShopApplication.get().getCustomerService().addCustomer(
        //        new CustomerDto("a", "b", "c", "d"));

        if (custser.getAllCustomers().isEmpty())
          setVisible(false);
        else
          setVisible(true);
      }
    });
  }
}