/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.web.pages;

import fi.muni.pa165.web.components.MenuPanel;
import fi.muni.pa165.web.components.HeaderPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public abstract class TemplatePage extends WebPage {
    private Component menuPanel;
    private Component headerPanel;
    
    public TemplatePage() {
        this.add(menuPanel = new MenuPanel(ComponentIDs.MENU_PANEL_ID));
        this.add(headerPanel = new HeaderPanel(ComponentIDs.HEADER_PANEL_ID));
    }
    
    private static class ComponentIDs {
        private static final String MENU_PANEL_ID = "menuPanel";   
        private static final String HEADER_PANEL_ID = "headerPanel";
    }

    /*
     *  Getre pre dalsie pouzinie, napriklad pre zmenu obrazka
     *  alebo aby sa nezobrazovalo menu - v login page
     */
    public Component getMenuPanel() {
        return menuPanel;
    }

    public Component getHeaderPanel() {
        return headerPanel;
    }
    
}
