package at.itb13.oculus.presentation.gwt.client.login.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginCheckServiceAsync {

	void isLoginCredentialsValid(String email, String password,
			AsyncCallback<Boolean> callback);

}
