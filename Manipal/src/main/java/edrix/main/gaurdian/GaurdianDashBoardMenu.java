package edrix.main.gaurdian;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import edrix.main.commons.ProfilePreferencesWindow;
import edrix.main.commons.User;
import edrix.main.gaurdian.GaurdianDashboardEvent.PostViewChangeEvent;

@SuppressWarnings({ "serial", "unchecked" })
public class GaurdianDashBoardMenu extends CustomComponent {

	public static final String ID = "dashboard-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private static final String STYLE_VISIBLE = "valo-menu-visible";
	
	private Label notificationsBadge;
    private Label reportsBadge;
    private Label performancesBadge;
    private Label scoresBadge;
    private Label interfacesbadge;
    private Label profileBadge;
    private Label eventsBadge;
    private MenuItem settingsItem;
	
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
        menuContent.setWidth("200px");
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildUserMenu());
        menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

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
	
	private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final User user = getCurrentUser();
        settingsItem = settings.addItem("", new ThemeResource(
                "images/profile-pic-300px.jpg"), null);
        settingsItem.setText(user.getFirstName() + " " + user.getLastName());
        settingsItem.addItem("Edit Profile", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ProfilePreferencesWindow.open(user, false,true);
            }
        });
        settingsItem.addItem("Preferences", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ProfilePreferencesWindow.open(user, true,true);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                Notification.show("Please Close the application");
            }
        });
        return settings;
    }
	
	private User getCurrentUser() {
        return (User) VaadinSession.getCurrent().getAttribute(
                User.class.getName());
    }
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
	
	 private Component buildMenuItems() {
	        CssLayout menuItemsLayout = new CssLayout();
	        menuItemsLayout.addStyleName("valo-menuitems");

	        for (final GaurdianViewType view : GaurdianViewType.values()) {
	            Component menuItemComponent = new ValoMenuItemButton(view);

	            /*if (view == StudentDashboardViewType.DASHBOARD) {
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
	            }*/

	            if (view == GaurdianViewType.NOTIFICATION) {
	                notificationsBadge = new Label();
	                notificationsBadge.setId(NOTIFICATIONS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                        notificationsBadge);
	            }
	            if (view == GaurdianViewType.SCORES) {
	                reportsBadge = new Label();
	                reportsBadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                        reportsBadge);
	            }
	            if (view == GaurdianViewType.PERFORMANCES) {
	                performancesBadge = new Label();
	                performancesBadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		performancesBadge);
	            }
	            
	            if (view == GaurdianViewType.REPORTS) {
	                scoresBadge = new Label();
	                scoresBadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		scoresBadge);
	            }
	            
	            if (view == GaurdianViewType.INTERFACES) {
	                interfacesbadge = new Label();
	                interfacesbadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		interfacesbadge);
	            }
	            
	            if (view == GaurdianViewType.PROFILES) {
	                profileBadge = new Label();
	                profileBadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		profileBadge);
	            }
	            
	            if (view == GaurdianViewType.EVENTS) {
	            	eventsBadge = new Label();
	            	eventsBadge.setId(REPORTS_BADGE_ID);
	                menuItemComponent = buildBadgeWrapper(menuItemComponent,
	                		eventsBadge);
	            }

	            menuItemsLayout.addComponent(menuItemComponent);
	        }
	        return menuItemsLayout;

	    }
	 private Component buildBadgeWrapper(final Component menuItemButton,
	            final Component badgeLabel) {
	        CssLayout dashboardWrapper = new CssLayout(menuItemButton);
	        dashboardWrapper.addStyleName("badgewrapper");
	        dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
	        badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
	        badgeLabel.setWidthUndefined();
	        badgeLabel.setVisible(false);
	        dashboardWrapper.addComponent(badgeLabel);
	        return dashboardWrapper;
	    }
	 public final class ValoMenuItemButton extends Button {

	        private static final String STYLE_SELECTED = "selected";

	        private final GaurdianViewType view;

	        public ValoMenuItemButton(final GaurdianViewType view) {
	            this.view = view;
	            setPrimaryStyleName("valo-menu-item");
	            setIcon(view.getIcon());
	            setCaption(view.getViewName().substring(0, 1).toUpperCase()
	                    + view.getViewName().substring(1));
	            addClickListener(new ClickListener() {
	                @Override
	                public void buttonClick(final ClickEvent event) {
	                    UI.getCurrent().getNavigator().navigateTo(view.getViewName());
	                }
	            });

	        }

	        @Subscribe
	        public void postViewChange(final PostViewChangeEvent event) {
	            removeStyleName(STYLE_SELECTED);
	            if (event.getView() == view) {
	                addStyleName(STYLE_SELECTED);
	            }
	        }
	    }
}
