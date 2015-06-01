package at.itb13.oculus.presentation.gwt.client;

import at.itb13.oculus.presentation.gwt.client.login.view.Login;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Index implements EntryPoint {
	public void onModuleLoad() {
		RootPanel.get().add(new Login());
	}
	
	public static void forward(Composite c) {
		RootPanel.get().clear();
		RootPanel.get().add(c);
	}
}