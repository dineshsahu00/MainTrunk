package edrix.main;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("edrix.MyAppWidgetset")
public class Edrix extends UI {

	public static final String HOME = "HOME";

	public Navigator edrixNavigator = new Navigator(this,this);
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
	
		EdrixHome home = new EdrixHome(edrixNavigator);
		registerNavigator(home);
	}

	private void registerNavigator(EdrixHome home) {
		edrixNavigator.addView(HOME, home);
		edrixNavigator.navigateTo(HOME);
	}

	

}
