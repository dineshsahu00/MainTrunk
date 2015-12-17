package edrix.components;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class HomePanel extends HorizontalLayout{
	
	public HomePanel(){
		super();
		createHeaderSection();
		createBodySection();
		registerComponents();
	}

	public void createHeaderSection() {		
		
		/*ThemeResource resource = new ThemeResource("images/edrix.PNG");
		Image image = new Image("", resource);
		image.setHeight(100, Unit.PIXELS);
		image.setWidth(450, Unit.PIXELS);*/
	}
	
	public void createBodySection(){
		
	}
	
	public void registerComponents(){
	}
}
