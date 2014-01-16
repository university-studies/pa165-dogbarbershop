package fi.muni.pa165.dto;

/**
 *
 * @author Oliver Pentek
 */
public enum UserRole {
    ADMIN("admin"),
    USER("user");
    
    private final String text;

    private UserRole(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }
}
