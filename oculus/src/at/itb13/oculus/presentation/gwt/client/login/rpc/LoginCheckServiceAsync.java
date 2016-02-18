package at.itb13.oculus.presentation.gwt.client.login.rpc;

import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginCheckServiceAsync {

	void isLoginCredentialsValid(String email, String password,
			AsyncCallback<Patient> callback);

}
