package at.itb13.oculus.presentation.view;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 22.04.2015
 */
public class StartProcessController {
	
	final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};
	@FXML
	private ImageView _imageView;
	@FXML
	private ProgressBar _progressBar;

	private Stage _dialogStage;
	@FXML
	private void initialize() {
		_imageView = new ImageView();
        final Image image = new Image("file:../ApplicationResources/Images/Auge.jpg");
        _imageView.setImage(image);
        initProgressbar();
	}

	/**
	 * 
	 */
	private void initProgressbar() {
		_progressBar = new ProgressBar(0);
		ProgressIndicator indicator = new ProgressIndicator();
	}

	/**
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		
	}
}
