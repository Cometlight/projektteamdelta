package at.itb13.oculus.presentation.gwt.client.login.view;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResources extends ClientBundle {
	/**
	   * Sample CssResource.
	   */
	   public interface Style extends CssResource {
	      String label();
	      String box();
	      String button();
	      String error();
	   }

	   @Source("Login.css")
	   Style style();
}
