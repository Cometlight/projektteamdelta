package at.itb13.oculus.presentation.gwt.client.login.view;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
import at.itb13.oculus.presentation.gwt.client.appointmentRequestForm.view.AppointmentRequestForm;
import at.itb13.oculus.presentation.gwt.client.login.rpc.FutureAppointmentCheckService;
import at.itb13.oculus.presentation.gwt.client.login.rpc.FutureAppointmentCheckServiceAsync;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckService;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
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

	private boolean validInput;

	@UiTemplate("Login.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	@UiField(provided = true)
	final LoginResources res;

	public Login() {
		this.res = GWT.create(LoginResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));

		emailErrorLabel.setVisible(false);
		passwordErrorLabel.setVisible(false);
		loginErrorLabel.setVisible(false);
		progressLabel.setVisible(false);
	}
	
	@UiHandler({"emailBox", "passwordBox"})
	void onActionPasswordBox(KeyPressEvent event) {
		login();
	}

	@UiHandler("loginButton")
	void onClickLoginButton(ClickEvent event) {
		login();
	}
	
	void login() {
		if (validInput) {
			final String email = emailBox.getText();
			String password = passwordBox.getText();

			// Check the patients login credentials. If they are correct, forward to the next page.
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					progressLabel.setVisible(false);
					Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
				}

				@Override
				public void onSuccess(Boolean loginCredentialsValid) {
					progressLabel.setVisible(false);
					if (loginCredentialsValid) {
						
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
									Index.forward(new AppointmentOverview(email));
								} else {
									Index.forward(new AppointmentRequestForm());
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
			loginCheckService
					.isLoginCredentialsValid(email, password, callback);
		}
	}

	@UiHandler("emailBox")
	void onEmailBoxChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() <= 0) {
			emailErrorLabel.setText(emailErrorMissing);
			emailErrorLabel.setVisible(true);
			validInput = false;
		} else if (!isEmailValid(event.getValue())) {
			emailErrorLabel.setText(emailErrorInvalid);
			emailErrorLabel.setVisible(true);
			validInput = false;
		} else {
			emailErrorLabel.setVisible(false);
			validInput = true;
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
			validInput = false;
		} else {
			passwordErrorLabel.setVisible(false);
			validInput = true;
		}
	}
}
