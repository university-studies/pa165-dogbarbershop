/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dto;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
 
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
 
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        if (v == null) {
            return null;
        }
        
        return formatter.parseLocalDate(v);
    }
 
    @Override
    public String marshal(LocalDate v) throws Exception {
        if (v == null) {
            return null;
        }
            
        return formatter.print(v);
    }
 
    private final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
}