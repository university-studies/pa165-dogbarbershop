package fi.muni.pa165.web.pages;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.idao.CustomerDao;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.utils.CustomerConverter;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

/**
 * @author Jan Pacner
 */
public class CustomerPage extends TemplatePage {
  /* HTML element IDs */
  private static final String FORM_LISTING    = "form_listing";
  private static final String SPAN_ROW00      = "span_row00";
  private static final String SPAN_NAME       = "span_name";
  private static final String SPAN_SURNAME    = "span_surname";
  private static final String SPAN_PHONE      = "span_phone";
  private static final String SPAN_ADDR       = "span_addr";
  private static final String BTN_DEL         = "btn_del";
  private static final String BTN_EDIT        = "btn_edit";

  private static final String FORM_WHOLE      = "form_whole";
  private static final String INPUT_NAME      = "input_name";
  private static final String INPUT_SURNAME   = "input_surname";
  private static final String INPUT_PHONE     = "input_phone";
  private static final String INPUT_ADDR      = "input_addr";
  private static final String BTN_SUBMIT      = "btn_submit";
  //private static final String REFRESH_LINK = "refresh";
  private static final String H2_ACTION = "h2_action";
  private static final String FEEDBACK_PANEL="feedback";

  private Label actionLbl;
  private boolean isUpdateButton;
  private final CustomerService serv;

  public CustomerPage() {
    serv = DogBarberShopApplication.get().getCustomerService();
    
    final FeedbackPanel feedback = new FeedbackPanel(FEEDBACK_PANEL);
        this.add(feedback);

    CompoundPropertyModel<CustomerDto> mod = new CompoundPropertyModel<>(new CustomerDto());
    final Form<CustomerDto> form_whole = new Form<CustomerDto>(FORM_WHOLE, mod) {
      @Override
      protected void onSubmit() {
        super.onSubmit();
        if (CustomerPage.this.isUpdateButton) {
          //serv.updateDog(this.getModel().getObject());
          serv.updateCustomer(this.getModelObject());
          this.setModelObject(new CustomerDto());
          actionLbl.setDefaultModelObject(CustomerPage.this.getLocalizer()
                  .getString("CustomerPage.action.new_cust", CustomerPage.this));
          isUpdateButton = false;
        }
        else {
          serv.addCustomer(this.getModelObject());
          this.setModelObject(new CustomerDto());
        }
      }
    };

    form_whole.add(new RequiredTextField(INPUT_NAME, mod.bind("name")));
    form_whole.add(new RequiredTextField(INPUT_SURNAME, mod.bind("surname")));
    form_whole.add(new RequiredTextField(INPUT_PHONE, mod.bind("phone")));
    form_whole.add(new RequiredTextField(INPUT_ADDR, mod.bind("address")));
    form_whole.add(new Button(BTN_SUBMIT));
    add(form_whole);

    final Form form_listing = new Form<CustomerDto>(FORM_LISTING, new Model()) {
      @Override
      protected void onSubmit() {
        super.onSubmit();
        if (CustomerPage.this.isUpdateButton) {
          form_whole.setModelObject(this.getModelObject());
          actionLbl.setDefaultModelObject(CustomerPage.this.getLocalizer()
                  .getString("CustomerPage.action.edit_cust", CustomerPage.this));
        }
        else {
          boolean fail = false;
          try { serv.deleteCustomer(this.getModelObject()); }
          catch (Exception e) {
            fail = true;
            actionLbl.setDefaultModelObject(CustomerPage.this.getLocalizer()
                    .getString("CustomerPage.action.del_failed", CustomerPage.this));
          }
          if (! fail) form_whole.setModelObject(new CustomerDto());
        }
      }
    };

    form_listing.setOutputMarkupId(true);
    form_listing.add(new ListView<CustomerDto>(SPAN_ROW00,
            new LoadableDetachableModel<List<CustomerDto>>() {
              @Override
              protected List<CustomerDto> load() {
                return CustomerPage.this.serv.getAllCustomers();
              }
            })
    {
      @Override
      protected void populateItem(ListItem<CustomerDto> li) {
        final CustomerDto dto = li.getModelObject();
        li.add(new Label(SPAN_NAME, dto.getName()));
        li.add(new Label(SPAN_SURNAME, dto.getSurname()));
        li.add(new Label(SPAN_PHONE, dto.getPhone()));
        li.add(new Label(SPAN_ADDR, dto.getAddress()));
        li.add(new Button(BTN_DEL) {
          @Override
          public void onSubmit() {
            super.onSubmit();
            isUpdateButton = false;
            form_listing.setModelObject(dto);
            actionLbl.setDefaultModelObject(CustomerPage.this.getLocalizer()
                    .getString("CustomerPage.action.new_cust", CustomerPage.this));
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

    add(form_listing);
    add(actionLbl = new Label(H2_ACTION, new Model()));
    actionLbl.setDefaultModelObject(CustomerPage.this.getLocalizer()
            .getString("CustomerPage.action.new_cust", CustomerPage.this));
  }
}