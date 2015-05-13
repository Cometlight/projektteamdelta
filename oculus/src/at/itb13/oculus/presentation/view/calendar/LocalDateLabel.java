package at.itb13.oculus.presentation.view.calendar;

import java.time.LocalDate;

import javafx.scene.control.Label;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date May 13, 2015
 */
public class LocalDateLabel extends Label {
	private LocalDate _localDate;
	
	public LocalDateLabel() {
		super();
	}
	
	public LocalDateLabel(LocalDate localDate) {
		this(localDate, localDate.toString());
	}
	
	public LocalDateLabel(LocalDate localDate, String text) {
		super(text);
		setLocalDate(localDate);
		this.getStyleClass().add("LocalDateLabel");
	}

	public LocalDate getLocalDate() {
		return _localDate;
	}

	public void setLocalDate(LocalDate localTime) {
		_localDate = localTime;
		if(_localDate.equals(LocalDate.now())) {
			setStyle("-fx-background-color: pink");
		}
	}
}
