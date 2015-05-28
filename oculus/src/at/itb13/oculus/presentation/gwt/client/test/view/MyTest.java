package at.itb13.oculus.presentation.gwt.client.test.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MyTest extends Composite {

	private static MyTestUiBinder uiBinder = GWT.create(MyTestUiBinder.class);

	
	interface MyTestUiBinder extends UiBinder<Widget, MyTest> {
	}

	public MyTest() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
