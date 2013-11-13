/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 * @author Oliver Pentek
 */
public class EntrancePage extends WebPage {
    public EntrancePage() {
        add(new Label("message", "Caaaute baby!"));
    }
}
