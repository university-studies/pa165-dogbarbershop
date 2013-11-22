package fi.muni.pa165.web.components;

import fi.muni.pa165.web.pages.DogServicePage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>, Oliver Pentek
 */
public class HeaderPanel extends Panel {
    
    public HeaderPanel(String id) {
        super(id);
        this.add(new BookmarkablePageLink("homePageLink", DogServicePage.class));
        
    }
}
