package fi.muni.pa165.web.components;

import fi.muni.pa165.web.SessionAttributes;
import fi.muni.pa165.web.pages.DogServicePage;
import fi.muni.pa165.web.pages.SignInPage;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>, Oliver Pentek
 */
public class HeaderPanel extends Panel {
    
    public HeaderPanel(String id) {
        super(id);
        this.add(new BookmarkablePageLink("homePageLink", DogServicePage.class));
        this.add(new Link("logoutLink") {
            
            @Override
            public void onClick() {
                getSession().removeAttribute(SessionAttributes.CURRENT_USER.getText());
                setResponsePage(SignInPage.class);
            }
            
        });
    }
}
