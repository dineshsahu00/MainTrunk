package edrix.main.student;

import java.util.Spliterator;
import java.util.function.Consumer;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;

public class PerformancesView extends Panel implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518670291651605150L;

	@Override
	public void forEach(Consumer<? super Component> action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spliterator<Component> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Performances View not Implemented!");
	}

	/**
	 * @param args
	 */
	

}
