package fi.muni.pa165.web;
import fi.muni.pa165.web.pages.EntrancePage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
/**
 *
 * @author Oliver Pentek
 */
public class DogBarberShopApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return EntrancePage.class;
    }
    
}
