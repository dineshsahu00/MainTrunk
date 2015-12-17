package edrix.main.organisation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class OrganisationDashBoard extends HorizontalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9025819096419248272L;

	/**
	 * 
	 */

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Enter Organisation Login page");
		
	}

	public OrganisationDashBoard(){
		setSizeFull();
        addStyleName("mainview");

        addComponent(new OrganisationDashBoardMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);	
	}
}
