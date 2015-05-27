package at.itb13.oculus.presentation.gwt.client.login.view;

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
			
			loginCheckService.isLoginCredentialsValid(email, password,
					callback);
		}
	}

	@UiHandler("emailBox")
	void onEmailBoxChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 6) { // TODO
			emailErrorLabel.setVisible(true);
			validInput = false;
		} else {
			emailErrorLabel.setVisible(false);
			validInput = true;
		}
	}

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
