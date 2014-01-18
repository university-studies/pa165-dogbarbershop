package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.web.DogBarberShopApplication;
import fi.muni.pa165.web.converter.LocalDateConverter;
import fi.muni.pa165.web.validator.LocalDateValidator;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.joda.time.LocalDate;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.joda.time.format.DateTimeFormat;

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
  private static final String INPUT_NAME      = "name";
  private static final String INPUT_BREED     = "breed";
  private static final String INPUT_BIRTHDATE = "birthDate";  // LocalDate
  private static final String SELECT_OWNER    = "owner";  // CustomerDto
  private static final String BTN_SUBMIT      = "btn_submit";
  private static final String H2_ACTION       = "h2_action";

  private final Label action;
  private final Form form_dog_whole;
  private final Form form_listing;
  private final TextField date;
  private DropDownChoice select_owner;
  private final Button btn_submit;
  private CustomerDto default_selection = null;
  private ListView lview;

  private enum State {NEW_DOG, EDIT_DOG};
  private State st = State.NEW_DOG;
  private final DogService dogser;
  private final CustomerService custser;

  private static DogDto newDogDto() {
    return new DogDto("", "", new LocalDate(2013, 1, 1), null);
  }

  public DogPage() {
    dogser = DogBarberShopApplication.get().getDogService();
    custser = DogBarberShopApplication.get().getCustomerService();
    
    final FeedbackPanel feedback = new FeedbackPanel("feedback");
    add(feedback);
    // writes into the feedback panel
    //getSession().info("MY INFO");

    add(form_listing = new Form<DogDto>(FORM_LISTING, new Model<DogDto>()) {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true);
        add(lview = new ListView<DogDto>(SPAN_ROW00,
                new LoadableDetachableModel<List<DogDto>>() {
                  @Override
                  protected List<DogDto> load() {
                    return DogPage.this.dogser.getAllDogs();
                  }
                })
        {
          @Override
          protected void populateItem(final ListItem<DogDto> li) {
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
                dogser.deleteDog(li.getModelObject());
                // re-register (i.e. rewrite the old one) to update view
                form_listing.add(lview);
              }
            });

            li.add(new Button(BTN_EDIT) {
              @Override
              public void onSubmit() {
                super.onSubmit();
                form_dog_whole.setModelObject(li.getModelObject());
                // preselect the right owner
                select_owner.setModelObject(li.getModelObject().getOwner());
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
    // set as default "page action"
    action.setDefaultModelObject(DogPage.this.getLocalizer()
            .getString("DogPage.action.new_dog", DogPage.this));

    form_dog_whole = new Form<>(FORM_DOG_WHOLE,
            new CompoundPropertyModel<>(newDogDto()));
    form_dog_whole.add(new RequiredTextField(INPUT_NAME));
    form_dog_whole.add(new RequiredTextField(INPUT_BREED));

    date = new TextField(INPUT_BIRTHDATE, LocalDate.class);
    date.add(new LocalDateValidator());
    date.add(new DatePicker() {
      @Override
      protected String getDatePattern() {
        String p = super.getDatePattern();
        String p_new = DateTimeFormat.patternForStyle(LocalDateConverter.STYLE,
                this.getLocale());
        if (! p_new.equals(p)) return p_new;
        return p;
      }
    });
    form_dog_whole.add(date);

    form_dog_whole.add(select_owner = new DropDownChoice(SELECT_OWNER,
            new Model<>(default_selection), custser.getAllCustomers(),
            new ChoiceRenderer<CustomerDto>("name", "id")));
    form_dog_whole.add(btn_submit = new Button(BTN_SUBMIT) {
      @Override
      public void onSubmit() {
        super.onSubmit();

        if (select_owner.getModelObject() == null) {
          action.setDefaultModelObject(DogPage.this.getLocalizer()
                  .getString("DogPage.action.no_owner", DogPage.this));
          return;
        }

        // update DTO
        ((DogDto)form_dog_whole.getModelObject()).setOwner(
                (CustomerDto)select_owner.getModelObject());

        if (st == State.EDIT_DOG) {
          // next state
          st = State.NEW_DOG;
          // save DTO to DB
          dogser.updateDog((DogDto)form_dog_whole.getModelObject());
        }
        // State.NEW_DOG
        else {
          // save DTO to DB
          dogser.addDog((DogDto)form_dog_whole.getModelObject());
        }

        // reinitialize everything
        form_dog_whole.setModelObject(newDogDto());
        select_owner.setModelObject(null);
        action.setDefaultModelObject(DogPage.this.getLocalizer()
                .getString("DogPage.action.new_dog", DogPage.this));
      }
    });
    form_dog_whole.setDefaultButton(btn_submit);
    add(form_dog_whole);
  }
}
