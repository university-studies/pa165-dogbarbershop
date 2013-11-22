package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.util.List;
import org.apache.wicket.AttributeModifier;
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

  //FIXME find translatable solution
  private Button btn_submit;

  //isUpdateButton;
  private enum State {NEW_DOG, EDIT_DOG};
  private State st = State.NEW_DOG;
  private final DogService dogser;
  private final CustomerService custser;
  private DogDto to_edit = null;

  private static DogDto newDogDto() {
    return new DogDto("Alik", "yorkshire", new LocalDate(2013, 1, 1), null);
  }

  public DogPage() {
    dogser = DogBarberShopApplication.get().getDogService();
    custser = DogBarberShopApplication.get().getCustomerService();
    to_edit = newDogDto();

    add(new Form<DogDto>(FORM_LISTING, new Model<DogDto>()) {
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
            final DogDto dto = li.getModelObject();
            li.add(new Label(SPAN_NAME, dto.getName()));
            li.add(new Label(SPAN_BREED, dto.getBreed()));
            li.add(new Label(SPAN_BIRTHDATE, dto.getBirthDate()));
            CustomerDto o = dto.getOwner();
            li.add(new Label(SPAN_OWNER, o.getSurname() + " " + o.getName()));

            li.add(new Button(BTN_DEL) {
              @Override
              public void onSubmit() {
                super.onSubmit();
                if (dto == to_edit) {
                  st = State.NEW_DOG;
                  to_edit = newDogDto();
                }
                dogser.deleteDog(dto);
              }
            });

            li.add(new Button(BTN_EDIT) {
              @Override
              public void onSubmit() {
                super.onSubmit();
                to_edit = dto;
                st = State.EDIT_DOG;
              }
            });
          }
        });
      }
    });

    add(new Label(H2_ACTION, new Model()) {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(AttributeModifier.replace("key", "DogPage.action.new_dog"));
      }

      // called once per request on components before they are about to be rendered
      @Override
      protected void onConfigure() {
        super.onConfigure();

        //FIXME validate inputs
        if (custser.getAllCustomers().isEmpty()) {
          add(AttributeModifier.replace("key", "DogPage.action.no_cust"));
        }
        else {
          switch (st) {
            case NEW_DOG:
              add(AttributeModifier.replace("key", "DogPage.action.new_dog"));
            case EDIT_DOG:
              add(AttributeModifier.replace("key", "DogPage.action.edit_dog"));
            default:
              add(AttributeModifier.replace("key", "DogPage.action.init"));
          }
        }
      }
    });

    add(new Form<DogDto>(FORM_DOG_WHOLE, new Model()) {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(new RequiredTextField(INPUT_NAME, new PropertyModel(to_edit, "name")));
        add(new RequiredTextField(INPUT_BREED, new PropertyModel(to_edit, "breed")));
        //FIXME add localization
        // DogBarberShopApplication.get().getResourceSettings().getLocalizer().getString("h2_action", action);
        add(new RequiredTextField(INPUT_BIRTHDATE, new PropertyModel(to_edit, "birthDate")));
        add(new DropDownChoice<>(
                SELECT_OWNER,
                new PropertyModel<CustomerDto>(to_edit, "owner"),
                new LoadableDetachableModel<List<CustomerDto>>() {
                  @Override
                  protected List<CustomerDto> load() {
                    return custser.getAllCustomers();
                  }
                }));
        add(new Button(BTN_SUBMIT));
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

      @Override
      protected void onSubmit() {
        super.onSubmit();

        //FIXME validate inputs
        switch (st) {
          case EDIT_DOG:
            dogser.updateDog(to_edit);
            st = State.NEW_DOG;
            to_edit = newDogDto();
            break;
          case NEW_DOG:
            dogser.addDog(to_edit);
            break;
          default:
        }
      }
    });

    //new PropertyModel<Person>(employee, "managedBy"),
    //new LoadableDetachableModel<List<Person>>() {
    //@Override
    //protected Object load() {
    //  return Person.getManagers();
    //}
    //}

//    add(new Form<DogDto>(FORM_DOG_WHOLE, new Model<DogDto>()) {
//      String sel;
//      private List<String> ch;  // available choices
//      private List<DogDto> ch_dto;  // pertaining DTOs
//
//      @Override
//      protected void onInitialize() {
//        super.onInitialize();
//        //CompoundPropertyModel<DogDto> m = (CompoundPropertyModel<DogDto>)getModel();
//        PropertyModel<DogDto> m = (PropertyModel<DogDto>)getModel();
//        add(new TextField<>(INPUT_NAME, new PropertyModel(m, "name")));
//        add(new TextField(INPUT_BREED, m.bind("breed")));
//        add(new TextField(INPUT_BIRTHDATE, m.bind("birthDate")));
//        ch = new ArrayList<>();
//        ch_dto = new ArrayList<>();
//        ch.add("none");
//        ch_dto.add(null);
//        // FIXME(class, "field_name")
//        PropertyModel<String> mm = new PropertyModel<>(this, "sel");
//        add(new DropDownChoice<>(SELECT_OWNER, mm,
//                //Arrays.asList(new String[] { "Google", "Bing", "Baidu" };
//                new ArrayList<String>()));
//        //add(new RequiredTextField(SELECT_OWNER, mod.bind("owner")));
//        //add(btn_submit);
//      }
//
//      // called once per request on components before they are about to be rendered
//      @Override
//      protected void onConfigure() {
//        super.onConfigure();
//        if (this.getModelObject() == null) {
//          DogDto d = newDogDto();
//          if (d == null) {
//            DogPage.this.st = State.NO_CUST;
//            setVisible(false);
//          }
//          else {
//            //this.setDefaultModelObject(d);
//            this.setModelObject(d);
//            DogPage.this.st = State.EMPTY;
//            setVisible(true);
//          }
//        }
//      }
//
//
//      @Override
//      protected void onSubmit() {
//        super.onSubmit();
//        switch (DogPage.this.st) {
//          case NO_CUST:
//            action.add(AttributeModifier.replace("key", "DogPage.action.no_cust"));
//            add(AttributeModifier.replace("style", "display:none;"));
//            break;
//          case EMPTY:
//            action.add(AttributeModifier.replace("key", "DogPage.action.empty"));
//          case NEW_DOG:
//            action.add(AttributeModifier.replace("key", "DogPage.action.new_dog"));
//          case EDIT_DOG:
//            action.add(AttributeModifier.replace("key", "DogPage.action.edit_dog"));
//          default:
//            action.add(AttributeModifier.replace("key", "DogPage.action.init"));
//        }
//        if (DogPage.this.btn_submit.getFlag(FLAG_RESERVED1)) {
//          //dogser.updateDog(this.getModel().getObject());
//          dogser.updateDog(getModelObject());
//          setModelObject(newDogDto());
//          action.setDefaultModelObject(LBL_NEW_CUST);
//          isUpdateButton = false;
//        }
//        else {
//          dogser.addDog(getModelObject());
//          setModelObject(newDogDto());
//        }
//      }
//    });
  }
}