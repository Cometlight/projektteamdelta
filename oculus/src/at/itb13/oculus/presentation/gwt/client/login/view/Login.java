package at.itb13.oculus.presentation.gwt.client.login.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Transient;

import at.itb13.oculus.presentation.gwt.client.Index;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckService;
import at.itb13.oculus.presentation.gwt.client.login.rpc.LoginCheckServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
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

	@UiField
	TextBox emailBox;

	@UiField
	Label emailErrorLabel;
//	private final String emailErrorMissing

	@UiField
	TextBox passwordBox;

	@UiField
	Label passwordErrorLabel;

	@UiField
	Button loginButton;

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
	}

	@UiHandler("loginButton")
	void onClickLoginButton(ClickEvent event) {
		if (validInput) {
			String email = emailBox.getText();
			String password = passwordBox.getText();

			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
				}

				@Override
				public void onSuccess(Boolean loginCredentialsValid) {
					if (loginCredentialsValid) {
						Index.forward(new AppointmentOverview());
					} else {
						loginErrorLabel.setVisible(true);
					}
				}
			};

			loginCheckService
					.isLoginCredentialsValid(email, password, callback);
		}
	}

	@UiHandler("emailBox")
	void onEmailBoxChange(ValueChangeEvent<String> event) {
//		if (isEmailValid(event.getValue())) {
//			emailErrorLabel.setVisible(true);
//			validInput = false;
//		} else {
			emailErrorLabel.setVisible(false);
			validInput = true;
//		}
	}

	/**
	 * Checks if the given email is valid; i.e. RegEx check. However, it does
	 * not check if the email exists in the database. Info: This method is not
	 * placed in the Application layer so we don't have to send an AJAX request
	 * only for doing a simple RegEx check.
	 * 
	 * @param email
	 *            the email to be checked
	 * @return true if the format of the email is valid
	 */
//	@Transient
//	private boolean isEmailValid(String email) {
//		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//		Pattern pattern = Pattern.compile(emailPattern);
//		Matcher matcher = pattern.matcher(email);
//
//		return matcher.matches();
//	}

	@UiHandler("passwordBox")
	void onPasswordBoxChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 6) { // TODO
			passwordErrorLabel.setVisible(true);
			validInput = false;
		} else {
			passwordErrorLabel.setVisible(false);
			validInput = true;
		}
	}
}
