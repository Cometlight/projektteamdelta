package at.itb13.teamD.presentation;

import java.time.LocalTime;

import javafx.scene.control.Label;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 11.05.2015
 */
public class LocalTimeLabel extends Label {
	private LocalTime _localTime;
	
	public LocalTimeLabel(LocalTime localTime) {
		this(localTime, localTime.toString());
	}
	
	public LocalTimeLabel(LocalTime localTime, String text) {
		super(text);
		_localTime = localTime;
		this.getStyleClass().add("LocalTimeLabel");
	}

	public LocalTime getLocalTime() {
		return _localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		_localTime = localTime;
	}
}