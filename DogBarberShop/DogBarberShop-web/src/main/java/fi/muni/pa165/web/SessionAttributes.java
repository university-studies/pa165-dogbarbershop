package fi.muni.pa165.web;

/**
 *
 * @author Oliver Pentek
 */
public enum SessionAttributes {
        CURRENT_USER("currentUser");
        
        private final String text;

    private SessionAttributes(String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }

}
