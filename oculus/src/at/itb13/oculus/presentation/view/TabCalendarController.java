package at.itb13.oculus.presentation.view;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import at.itb13.oculus.presentation.OculusMain;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 30.04.2015
 */
public class TabCalendarController {

	@FXML
	private WebView _webView;
	private OculusMain _main;
	
	//general Methods
	 public void setMain(OculusMain main) {
	        _main = main;
	  }
	 
	 @FXML
	 private void initialize() {
		 final WebEngine engine = _webView.getEngine();
		 engine.load("http://www.google.at/");
	 }

}
