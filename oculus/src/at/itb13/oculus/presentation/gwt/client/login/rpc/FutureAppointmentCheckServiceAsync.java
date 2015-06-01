package at.itb13.oculus.presentation.gwt.client.login.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FutureAppointmentCheckServiceAsync {

	void hasFutureAppointment(AsyncCallback<Boolean> callback);

}
