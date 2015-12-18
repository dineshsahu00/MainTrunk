package edrix.main.gaurdian;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

import edrix.main.student.EventsView;
import edrix.main.student.InterFacesView;
import edrix.main.student.PerformancesView;
import edrix.main.student.ProfileView;
import edrix.main.student.ReportsView;
import edrix.main.student.ScoresView;

public enum GaurdianViewType {
	NOTIFICATION("notification",NotificationsView.class,FontAwesome.BELL, true),
    SCORES("scores", ScoresView.class, FontAwesome.TABLE,false),
    PERFORMANCES("performances",PerformancesView.class,FontAwesome.BARCODE,false),
    REPORTS("reports",ReportsView.class,FontAwesome.FILE_TEXT,false),
    INTERFACES("interfaces",InterFacesView.class,FontAwesome.BRIEFCASE,false),
    PROFILES("profiles",ProfileView.class,FontAwesome.PENCIL,false),
    EVENTS("events",EventsView.class,FontAwesome.EYE,false);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private GaurdianViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static GaurdianViewType getByViewName(final String viewName) {
    	GaurdianViewType result = null;
        for (GaurdianViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
