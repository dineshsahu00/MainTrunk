package edrix.main;

import javax.management.NotificationFilter;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import edrix.dto.LoginDto;
import edrix.main.commons.User;
import edrix.main.gaurdian.GaurdianDashBoard;
import edrix.main.organisation.OrganisationDashBoard;
import edrix.main.student.StudentDashBoard;
import edrix.services.LoginService;

/**
 * @Author Dinesh 
 */
public class EdrixHome extends GridLayout implements View {

	private static final long serialVersionUID = -2579929728879240007L;
	public Navigator navigator;
	
	public static final String STUDENT_DASHBOARD = "STUDENT_DASHBOARD";
	
	public static final String GAURDAIAN_DASHBOARD = "GAURDIAN_DASHBOARD";
	
	public static final String ORGANISATION_DASHBOARD = "ORGANISATION_DASHBOARD";

	public EdrixHome(Navigator navigator) {
		super(1, 4);
		this.navigator = navigator;
		init();
	}

	private void init() {

		this.setStyleName("outlined");
		this.setMargin(true);
		this.setSizeUndefined();
		Responsive.makeResponsive(this);
		createMenuBar(this);
		GridLayout bodySection = new GridLayout(2, 1);
		bodySection.setStyleName("backgroundimage");
		TabSheet tabsheet = new TabSheet();
		tabsheet.setHeight(360, Unit.PIXELS);
		tabsheet.setWidth(700, Unit.PIXELS);
		// Accordian Section
		AbsoluteLayout absLayout = new AbsoluteLayout();
		absLayout.setWidth("450px");
		absLayout.setHeight("270px");
		Accordion accordion = new Accordion();
		accordion.setWidth("217px");
		Layout tab1 = new VerticalLayout();
		tab1.addComponent(new Image(null, new ThemeResource("images/analytics.png")));
		accordion.addTab(tab1, "Desktop", new ThemeResource("images/desktop.png"));

		Layout tab2 = new VerticalLayout();
		tab2.addComponent(new Image(null, new ThemeResource("images/cloudText.png")));
		accordion.addTab(tab2, "CLOUD", new ThemeResource("images/cloud.png"));

		absLayout.addComponent(accordion, "left: 210px; top: 50px;");

		HorizontalLayout headerSection = createHeaderSection();
		FormLayout studentLogin = createStudentTab();

		FormLayout gaurdianLogin = createGaurdianTab();

		FormLayout organisationLayout = createOrganisationTab();

		tabsheet.addTab(studentLogin, "Student", new ThemeResource("images/student.png"));
		tabsheet.addTab(gaurdianLogin, "Gaurdian", new ThemeResource("images/gaurdian.png"));
		tabsheet.addTab(organisationLayout, "Institution", new ThemeResource("images/organisation.png"));
		bodySection.addComponent(tabsheet);
		bodySection.addComponent(absLayout);
		HorizontalLayout footerSection = createFooter();
		this.addComponent(headerSection);
		this.addComponent(bodySection);
		this.addComponent(footerSection);
		this.setComponentAlignment(footerSection, Alignment.BOTTOM_CENTER);
		// registerNavigator(Lay);
	}

	private FormLayout createOrganisationTab() {
		// Organisatin Login
		FormLayout organisationLayout = new FormLayout();
		organisationLayout.setCaption("Organisation Login");
		TextField organisationUserName = new TextField("User Name :");
		PasswordField organisationPassword = new PasswordField("Password :");
		HorizontalLayout organisationButtonLayout = new HorizontalLayout();
		organisationButtonLayout.setSpacing(Boolean.TRUE);
		Button organisationSignInButton = new Button("Sign in");
		Button organisationSignupButton = new Button("Sign Up");
		Link organisationforgotPwdLink = new Link("Forgot Password", null);
		organisationforgotPwdLink.setDescription("Need help with your Password");
		CheckBox organisationLoginCheck = new CheckBox();
		organisationLoginCheck.setCaption("Keep me Signed in");
		organisationLoginCheck.setValue(Boolean.TRUE);
		organisationButtonLayout.addComponent(organisationSignInButton);
		organisationButtonLayout.addComponent(organisationSignupButton);
		organisationLayout.addComponent(organisationUserName);
		organisationLayout.addComponent(organisationPassword);
		organisationLayout.addComponent(organisationButtonLayout);
		organisationLayout.addComponent(organisationforgotPwdLink);
		organisationLayout.addComponent(organisationLoginCheck);
		handleOrganisationLogin(organisationSignInButton, organisationUserName, organisationPassword);
		handleOrganisationSignUp(organisationSignupButton);
		return organisationLayout;
	}

	private HorizontalLayout createFooter() {
		// Footer Section
		HorizontalLayout footerSection = new HorizontalLayout();
		BrowserFrame browser = new BrowserFrame(null, new ThemeResource("webpages/footer.html"));
		browser.setWidth("1350px");
		browser.setHeight("120px");
		footerSection.addComponent(browser);
		return footerSection;
	}

	private FormLayout createGaurdianTab() {
		// Gaurdian Login
		FormLayout gaurdianLogin = new FormLayout();
		gaurdianLogin.setCaption("Gaurdian Login");
		TextField gaurdianUserName = new TextField("User Name :");
		PasswordField gaurdianPassword = new PasswordField("Password :");
		HorizontalLayout gaurdianButtonLayout = new HorizontalLayout();
		gaurdianButtonLayout.setSpacing(Boolean.TRUE);
		Button gaurdianSignInButton = new Button("Sign in");
		Button gaurdianSignupButton = new Button("Sign Up");
		Link gaurdianforgotPwdLink = new Link("Forgot Password", null);
		gaurdianforgotPwdLink.setDescription("Need help with your Password");
		CheckBox gaurdianLoginCheck = new CheckBox();
		gaurdianLoginCheck.setCaption("Keep me Signed in");
		gaurdianLoginCheck.setValue(Boolean.TRUE);
		gaurdianButtonLayout.addComponent(gaurdianSignInButton);
		gaurdianButtonLayout.addComponent(gaurdianSignupButton);
		gaurdianLogin.addComponent(gaurdianUserName);
		gaurdianLogin.addComponent(gaurdianPassword);
		gaurdianLogin.addComponent(gaurdianButtonLayout);
		gaurdianLogin.addComponent(gaurdianforgotPwdLink);
		gaurdianLogin.addComponent(gaurdianLoginCheck);
		handleGaurdianLogin(gaurdianSignInButton, gaurdianUserName, gaurdianPassword);
		handleGaurdianSignUp(gaurdianSignupButton);
		return gaurdianLogin;
	}

	private FormLayout createStudentTab() {
		// Student Section
		FormLayout studentLogin = new FormLayout();
		studentLogin.setCaption("Student Login");
		TextField studentUserName = new TextField("User Name :");
		PasswordField studentPassword = new PasswordField("Password :");
		HorizontalLayout studentButtonLayout = new HorizontalLayout();
		studentButtonLayout.setSpacing(Boolean.TRUE);
		Button signInButton = new Button("Sign in");
		Button signupButton = new Button("Sign Up");
		Link forgotPwdLink = new Link("Forgot Password", null);
		forgotPwdLink.setDescription("Need help with your Password");
		CheckBox studentLoginCheck = new CheckBox();
		studentLoginCheck.setCaption("Keep me Signed in");
		studentLoginCheck.setValue(Boolean.TRUE);
		studentLogin.addComponent(studentUserName);
		studentLogin.addComponent(studentPassword);
		studentButtonLayout.addComponent(signInButton);
		studentButtonLayout.addComponent(signupButton);

		studentLogin.addComponent(studentButtonLayout);
		studentLogin.addComponent(forgotPwdLink);
		studentLogin.addComponent(studentLoginCheck);
		handleStudentLogin(signInButton, studentUserName, studentPassword);
		handleStudentSignUp(signupButton);
		return studentLogin;
	}

	private HorizontalLayout createHeaderSection() {
		// Header Section
		HorizontalLayout headerSection = new HorizontalLayout();
		HorizontalLayout subsection = new HorizontalLayout();
		BrowserFrame browser = new BrowserFrame("", new ThemeResource("webpages/header.html"));
		browser.setWidth("800px");
		browser.setHeight("120px");
		subsection.addComponent(browser);
		ThemeResource resource = new ThemeResource("images/edrix.PNG");
		Image image = new Image("", resource);
		image.setHeight(120, Unit.PIXELS);
		image.setWidth(450, Unit.PIXELS);
		subsection.addComponent(image);
		headerSection.addComponent(subsection);
		return headerSection;
	}

	private void createMenuBar(final GridLayout homeLayout) {
		MenuBar barmenu = new MenuBar();
		homeLayout.addComponent(barmenu);
		MenuItem products = barmenu.addItem("Products", null, null);
		products.addItem("Edrix Desktop", null, null);
		products.addItem("Edrix Mobile", null, null);
		products.addItem("Edrix Online", null, null);
		products.addItem("Edrix Server", null, null);
		products.addItem("Edrix Licencing", null, null);
		MenuItem solutions = barmenu.addItem("Solutions", null, null);
		solutions.addItem("Industries", null, null);
		solutions.addItem("Department", null, null);
		solutions.addItem("capabilities", null, null);
		solutions.addItem("Data Environment", null, null);
		solutions.addItem("All Solutions", null, null);
		MenuItem learnings = barmenu.addItem("Learnings", null, null);
		learnings.addItem("Training & Tutorial", null, null);
		learnings.addItem("Product Demos", null, null);
		learnings.addItem("Webinars", null, null);
		learnings.addItem("Events", null, null);
		learnings.addItem("Gallery", null, null);
		MenuItem support = barmenu.addItem("Support", null, null);
		support.addItem("Product Support", null, null);
		support.addItem("Training", null, null);
		support.addItem("Consulting Service", null, null);
		support.addItem("Knowledge Base", null, null);
		support.addItem("Community", null, null);
		MenuItem partners = barmenu.addItem("Partners", null, null);
		partners.addItem("Acadmic Programs", null, null);
		partners.addItem("Alliance Partners", null, null);
		partners.addItem("Technology Partners", null, null);
		partners.addItem("Partner Resource Center", null, null);
		MenuItem about = barmenu.addItem("About", null, null);
		about.addItem("Mission & Vision", null, null);
		about.addItem("News Room", null, null);
		about.addItem("Contact Us", null, null);
	}

	public void handleStudentLogin(Button button, final TextField studentUserName,
			final PasswordField studentPassword) {
		
		button.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -1809072471885383781L;

			@Override
			public void buttonClick(ClickEvent event) {
				LoginDto loginDto = new LoginDto();
				loginDto.setUserId(studentUserName.getValue());
				loginDto.setPassword(studentPassword.getValue());
				LoginService loginService= new LoginService();
				boolean sucessFlag =loginService.validateStudentLogin(loginDto);
				User student = createStudentUser(loginDto);
				VaadinSession.getCurrent().setAttribute(User.class.getName(),student);
				if(sucessFlag){
					 try {
				            Thread.sleep(2000) ;
				        } catch (InterruptedException e) {
				            // TODO Auto-generated catch block
				           Notification.show("Error in Login Thread");
				        }
					StudentDashBoard studentDashBoard= new StudentDashBoard();
					navigator.addView(STUDENT_DASHBOARD, studentDashBoard);
					navigator.navigateTo(STUDENT_DASHBOARD);
				}else{
					Notification.show("Error Login!! Please check the Crediantials");
				}
			}
		});
	}

	public void handleStudentSignUp(Button button) {

	}
	
	public void handleGaurdianLogin(Button button, final TextField gaurdianUserName,
			final PasswordField gaurdianPassword) {
		
		button.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -1809072471885383781L;

			@Override
			public void buttonClick(ClickEvent event) {
				LoginDto gaurdianLoginDto = new LoginDto();
				gaurdianLoginDto.setUserId(gaurdianUserName.getValue());
				gaurdianLoginDto.setPassword(gaurdianPassword.getValue());
				LoginService loginService= new LoginService();
				boolean sucessFlag =loginService.validateGaurdianLogin(gaurdianLoginDto);
				User gaurdian = createGaurdianUser(gaurdianLoginDto);
				VaadinSession.getCurrent().setAttribute(User.class.getName(),gaurdian);
				if(sucessFlag){
					GaurdianDashBoard gaurdianDashBoard= new GaurdianDashBoard();
					navigator.addView(GAURDAIAN_DASHBOARD, gaurdianDashBoard);
					navigator.navigateTo(GAURDAIAN_DASHBOARD);
				}else{
					Notification.show("Error Login!! Please check the Crediantials");
				}
			}
		});
	}

	public void handleGaurdianSignUp(Button button) {

	}

	public void handleOrganisationLogin(Button button, final TextField organisationUserName,
			final PasswordField organisationPassword) {
		
		button.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -1809072471885383781L;

			@Override
			public void buttonClick(ClickEvent event) {
				LoginDto gaurdianLoginDto = new LoginDto();
				gaurdianLoginDto.setUserId(organisationUserName.getValue());
				gaurdianLoginDto.setPassword(organisationPassword.getValue());
				LoginService loginService= new LoginService();
				boolean sucessFlag =loginService.validateGaurdianLogin(gaurdianLoginDto);
				User organisation = createOrganisationUser(gaurdianLoginDto);
				VaadinSession.getCurrent().setAttribute(User.class.getName(),organisation);
				if(sucessFlag){
					OrganisationDashBoard organisationDashBoard= new OrganisationDashBoard();
					navigator.addView(ORGANISATION_DASHBOARD, organisationDashBoard);
					navigator.navigateTo(ORGANISATION_DASHBOARD);
				}else{
					Notification.show("Error Login!! Please check the Crediantials");
				}
			}
		});
	}

	public void handleOrganisationSignUp(Button button) {

	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the EDRIX");
	}

	
	private User createStudentUser(LoginDto loginDto){
		User user = new User();
		user.setFirstName("Dinesh");
		user.setLastName("Sahu");
		user.setLocation("Bangalore");
		user.setMale(Boolean.TRUE);
		user.setEmail("dinesh.sahu@neobytes.com");
		user.setRole("Student");
		user.setTitle("Mr.");
		user.setPhone("9739524018");
		user.setBio("Test");
		return user;
	}
	
	private User createGaurdianUser(LoginDto loginDto){
		User user = new User();
		user.setFirstName("Kali");
		user.setLastName("Sahu");
		user.setLocation("Odissa");
		user.setMale(Boolean.TRUE);
		user.setEmail("Kalicharan@gmail.com");
		user.setRole("Gaurdian");
		user.setTitle("Mr.");
		user.setPhone("9739524018");
		user.setBio("Test");
		return user;
	}
	
	private User createOrganisationUser(LoginDto loginDto){
		User user = new User();
		user.setFirstName("Neobytes");
		user.setLastName("Limited");
		user.setLocation("Bangalore");
		user.setMale(Boolean.TRUE);
		user.setEmail("Neobytes.llc@neobytes.com");
		user.setRole("Admin");
		user.setTitle("Mr.");
		user.setPhone("9739524018");
		user.setBio("Test");
		return user;
	}
}
