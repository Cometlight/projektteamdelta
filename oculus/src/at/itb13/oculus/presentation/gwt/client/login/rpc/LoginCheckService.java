package at.itb13.oculus.presentation.gwt.client.login.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LoginCheckService")
public interface LoginCheckService extends RemoteService {
	Boolean isLoginCredentialsValid(String email, String password);
}
