package edrix.main.student;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import edrix.main.commons.DashboardNavigator;

public class StudentDashBoard extends HorizontalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7376732299650940755L;

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Enter Student Login page");
		
	}

	public StudentDashBoard(){
		setSizeFull();
        addStyleName("mainview");

        addComponent(new StudentDashBoardMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);	
        new DashboardNavigator(content);
	}
}
