package fi.muni.pa165.web.components;

import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.service.ServiceService;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Oliver Pentek
 */
//public class PalettePanel extends Panel {
// //   private final ServiceService service;
//
//    public PalettePanel(String id, ServiceService service) {
////        super(id);
////        this.service = service;
////        this.initComponents();
////    }
////
////    private void initComponents() { 
////        
////        IChoiceRenderer<ServiceDto> renderer = new ChoiceRenderer<>();
////		
////		final Palette<ServiceDto> palette = new Palette<>(ComponentIDs.PALETTE,
////				new ListModel<Hosting>(selected),
////				new CollectionModel<Hosting>(listHosting),
////				renderer, 10, true);
////		
////		form.add(palette);
////    }
////    
////    private static class ComponentIDs {
////        public static final String PALETTE = "palette";
////    }
//    
//    
//}
