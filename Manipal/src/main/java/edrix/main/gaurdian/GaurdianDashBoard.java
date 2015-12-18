package edrix.main.gaurdian;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class GaurdianDashBoard extends HorizontalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4149507328894010790L;


	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Enter Gaurdian Login page");
		
	}

	public GaurdianDashBoard(){
		setSizeFull();
        addStyleName("mainview");

        addComponent(new GaurdianDashBoardMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);
        new GaurdianViewNavigator(content);
	}
}
