package at.itb13.oculus.presentation.gwt.client.login.view;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view.AppointmentRequestForm;
import at.itb13.oculus.presentation.gwt.client.login.rpc.FutureAppointmentCheckService;
import at.itb13.oculus.presentation.gwt.client.login.rpc.FutureAppointmentCheckServiceAsync;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckService;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckServiceAsync;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * After a successful login, the patient is selected in the ControllerFacade.
 * Furthermore, it can be provided when forwarding to the next webpage.
 */
public class Login extends Composite {
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	private final LoginCheckServiceAsync loginCheckService = GWT
			.create(LoginCheckService.class);
	private final FutureAppointmentCheckServiceAsync futureAppointmentCheckService = GWT
			.create(FutureAppointmentCheckService.class);

	@UiField
	TextBox emailBox;

	@UiField
	Label emailErrorLabel;
	private final String emailErrorMissing = "Please provide an email address";
	private final String emailErrorInvalid = "This is not a valid email address";

	@UiField
	TextBox passwordBox;

	@UiField
	Label passwordErrorLabel;

	@UiField
	Button loginButton;

	@UiField
	Label progressLabel;
	
	@UiField
	Label loginErrorLabel;

	private boolean validEmailInput;
	private boolean validPasswordInput;
	private boolean isLoggingIn;

	@UiTemplate("Login.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	public Login() {
		initWidget(uiBinder.createAndBindUi(this));

		emailErrorLabel.setVisible(false);
		passwordErrorLabel.setVisible(false);
		loginErrorLabel.setVisible(false);
		progressLabel.setVisible(false);
	}
	
	@UiHandler({"emailBox", "passwordBox"})
	void onActionPasswordBox(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			login();
		}
	}

	@UiHandler("loginButton")
	void onClickLoginButton(ClickEvent event) {
		login();
	}
	
	void login() {
		if (!isLoggingIn && validEmailInput && validPasswordInput) {
			final String email = emailBox.getText();
			String password = passwordBox.getText();

			// Check the patients login credentials. If they are correct, forward to the next page.
			AsyncCallback<Patient> callback = new AsyncCallback<Patient>() {
				@Override
				public void onFailure(Throwable caught) {
					progressLabel.setVisible(false);
					loginButton.setEnabled(true);
					isLoggingIn = false;
					Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
				}

				@Override
				public void onSuccess(final Patient loggedInPatient) {
					progressLabel.setVisible(false);
					if (loggedInPatient != null) {
						
						// If the patient has an appointment in the future, forward to AppointmentOverview,
						// otherwise forward to AppointmentRequestForm
						AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
							}
							@Override
							public void onSuccess(Boolean hasFutureAppointment) {
								if(hasFutureAppointment) {
									Index.forward(new AppointmentOverview(loggedInPatient));
								} else {
									Index.forward(new AppointmentRequestForm(loggedInPatient));
								}
							}
						};
						
						futureAppointmentCheckService.hasFutureAppointment(callback);
					} else {
						loginErrorLabel.setVisible(true);
					}
				}
			};

			progressLabel.setVisible(true);
			loginButton.setEnabled(false);
			isLoggingIn = true;
			loginCheckService
					.isLoginCredentialsValid(email, password, callback);
		}
	}

	@UiHandler("emailBox")
	void onEmailBoxChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() <= 0) {
			emailErrorLabel.setText(emailErrorMissing);
			emailErrorLabel.setVisible(true);
			validEmailInput = false;
		} else if (!isEmailValid(event.getValue())) {
			emailErrorLabel.setText(emailErrorInvalid);
			emailErrorLabel.setVisible(true);
			validEmailInput = false;
		} else {
			emailErrorLabel.setVisible(false);
			validEmailInput = true;
		}
	}

	/**
	 * Checks if the given email is valid; i.e. RegEx check. However, it does
	 * not check if the email exists in the database. <br>
	 * Info: This method is not placed in the Application layer so we don't have
	 * to send an AJAX request only for doing a simple RegEx check.
	 * 
	 * @param email
	 *            the email to be checked
	 * @return true if the format of the email is valid
	 */
	private boolean isEmailValid(String email) {
		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		RegExp regExp = RegExp.compile(emailPattern);
		MatchResult matcher = regExp.exec(email);

		return matcher != null;
	}

	@UiHandler("passwordBox")
	void onPasswordBoxChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() <= 0) {
			passwordErrorLabel.setVisible(true);
			validPasswordInput = false;
		} else {
			passwordErrorLabel.setVisible(false);
			validPasswordInput = true;
		}
	}
}
