package edrix.main.gaurdian;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.AcceptItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings({ "serial", "unchecked" })
public class GaurdianDashBoardMenu extends CustomComponent {

	public static final String ID = "dashboard-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private static final String STYLE_VISIBLE = "valo-menu-visible";
	
	public GaurdianDashBoardMenu(){
		setPrimaryStyleName("valo-menu");
	    setId(ID);
	    setSizeUndefined();
	    
	    setCompositionRoot(buildContent());
	}
	
	private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
       // menuContent.addComponent(buildUserMenu());
        menuContent.addComponent(buildToggleButton());
       // menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

	private Component buildTitle() {
        Label logo = new Label("EDRIX <strong>Dashboard</strong>",
                ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        return logoWrapper;
    }
	
	/*private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final User user = getCurrentUser();
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
        updateUserName(null);
        settingsItem.addItem("Edit Profile", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ProfilePreferencesWindow.open(user, false);
            }
        });
        settingsItem.addItem("Preferences", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ProfilePreferencesWindow.open(user, true);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                DashboardEventBus.post(new UserLoggedOutEvent());
            }
        });
        return settings;
    }*/
	
	private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
        valoMenuToggleButton.setIcon(FontAwesome.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }
	
	 /*private Component buildMenuItems() {
	        CssLayout menuItemsLayout = new CssLayout();
	        menuItemsLayout.addStyleName("valo-menuitems");

	        for (final DashboardViewType view : DashboardViewType.values()) {
	            Component menuItemComponent = new ValoMenuItemButton(view);

	            if (view == DashboardViewType.REPORTS) {
	                // Add drop target to reports button
	                DragAndDropWrapper reports = new DragAndDropWrapper(
	                        menuItemComponent);
	                reports.setSizeUndefined();
	                reports.setDragStartMode(DragStartMode.NONE);
	                reports.setDropHandler(new DropHandler() {

	                    @Override
	                    public void drop(final DragAndDropEvent event) {
	                        UI.getCurrent()
	                                .getNavigator()
	                                .navigateTo(
	                                        DashboardViewType.REPORTS.getViewName());
	                        Table table = (Table) event.getTransferable()
	                                .getSourceComponent();
	                       
	                    }

	                    @Override
	                    public AcceptCriterion getAcceptCriterion() {
	                        return AcceptItem.ALL;
	                    }

	                });
	                menuItemComponent = reports;
	            }

	            if (view == DashboardViewType.DASHBOARD) {
	                notificationsBadge = new Label();
	                notificationsBadge.setId(NOTIFICATIONS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                        notificationsBadge);
	            }
	            if (view == DashboardViewType.REPORTS) {
	                reportsBadge = new Label();
	                reportsBadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                        reportsBadge);
	            }

	            menuItemsLayout.addComponent(menuItemComponent);
	        }
	        return menuItemsLayout;

	    }*/
}
