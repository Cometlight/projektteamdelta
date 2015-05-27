package at.itb13.oculus.presentation.gwt.client;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date May 27, 2015
 */
public class FlowControl {
	private static FlowControl instance;
	private FlowControl() {}
	public static void go(Composite c) {
	    if (instance == null) instance = new FlowControl(); // not sure why we need this yet since everything is static.
	    RootPanel.get("application").clear();
	    RootPanel.get("application").getElement().getStyle().setPosition(Position.RELATIVE); // not sure why, but GWT throws an exception without this. Adding to CSS doesn't work.
	    // add, determine height/width, center, then move. height/width are unknown until added to document. Catch-22!
	    RootPanel.get("application").add(c);
	    int left = Window.getClientWidth() / 2 - c.getOffsetWidth() / 2; // find center
	    int top = Window.getClientHeight() / 2 - c.getOffsetHeight() / 2;
	    RootPanel.get("application").setWidgetPosition(c, left, top);
	    History.newItem(c.getTitle()); // TODO: need to change and implement (or override) this method on each screen
	}

	public static void go(String token) {
	    if (token == null) go(new Login());
//	    if (token.equals("cart")) go(new Cart());
	    if (token.equals("login")) go(new Login());
	    // Can probably make these constants in this class
	}
}
