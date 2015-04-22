package at.itb13.oculus.presentation.view;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 22.04.2015
 */
public class StartProcessController {
	
	@FXML
	private ImageView _imageView;
	@FXML
	private ProgressBar _progressBar;

	@FXML
	private void initialize() {
		_imageView = new ImageView();
        final Image image = new Image("file:ApplicationResources/Images/Auge.jpg");
        _imageView.setImage(image);
	}
}
