package at.itb13.oculus.presentation;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * A simple splash screen with an image, a progress indicator, and a progress text label.
 * Note: This class was designed based on <a href="https://gist.github.com/jewelsea/2305098">github.com/jewelsea/2305098</a>.
 * 
 * @author Daniel Scheffknecht
 * @date 23.04.2015
 */
public class SplashScreen {
	
	public interface InitCompletionHandler {
		public void complete();
	}
	 
	private static final String SPLASH_IMAGE_PATH = "file:ApplicationResources/Images/eye_big.png";
	private static final double SPLASH_IMAGE_WIDTH = 256d;
	private static final double SPLASH_IMAGE_HEIGHT = 256d;
	private static final double SPLASH_PROGESS_SIZE = 240d;
	private static final int SPLASH_TEXT_SIZE = 14;	// in px
	private static final double SPLASH_WIDTH = SPLASH_IMAGE_WIDTH;
	private static final double SPLASH_HEIGHT = SPLASH_IMAGE_HEIGHT + SPLASH_TEXT_SIZE;
	
	private Pane _splashLayout;
	private ProgressIndicator _splashProgressIndicator;
	private Label _splashProgressText;
	
	public SplashScreen() {
		init();
	}
	
	private void init() {
		ImageView splash = new ImageView(new Image(SPLASH_IMAGE_PATH));
		_splashProgressIndicator = new ProgressIndicator();
		_splashProgressText = new Label();
		_splashLayout = new AnchorPane();
        _splashLayout.getChildren().addAll(splash, _splashProgressIndicator, _splashProgressText);
		
        
        _splashProgressText.setLayoutY(SPLASH_IMAGE_HEIGHT - SPLASH_TEXT_SIZE/2d);
        _splashProgressText.setStyle("-fx-font-size: " + SPLASH_TEXT_SIZE + "px;");
        
		_splashProgressIndicator.setPrefWidth(SPLASH_PROGESS_SIZE);
		_splashProgressIndicator.setPrefHeight(SPLASH_PROGESS_SIZE);
        _splashProgressIndicator.setLayoutX(SPLASH_WIDTH/2d - SPLASH_PROGESS_SIZE/2d);
		_splashProgressIndicator.setLayoutY(SPLASH_IMAGE_HEIGHT/2d - SPLASH_PROGESS_SIZE/2d);
		
        _splashLayout.setStyle("-fx-padding: 0; -fx-background-color: white;");
	}
	
	/**
	 * Shows the splashscreen until the task is completed and executes initCompletionHandler.complete() afterwards.
	 * 
	 * @param initStage The stage in which the splash screen should be displayed.
	 * @param task The task that's executed while the splash screen is shown. TextProperty and ProgressProperty are bound.
	 * @param initCompletionHandler initCompletionHandler's complete() is executed after the task is completed and the splash screen has faded away.
	 */
	public void showSplash(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
		_splashProgressText.textProperty().bind(task.messageProperty());
		_splashProgressIndicator.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
            	_splashProgressIndicator.progressProperty().unbind();
            	_splashProgressIndicator.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), _splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();
 
                initCompletionHandler.complete();
            }
        });
        
        Scene splashScene = new Scene(_splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setWidth(SPLASH_WIDTH);
        initStage.setHeight(SPLASH_HEIGHT);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2d - SPLASH_WIDTH / 2d);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2d - SPLASH_HEIGHT / 2d);
        initStage.show();
    }
}
