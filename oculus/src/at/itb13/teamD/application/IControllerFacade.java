package at.itb13.teamD.application;

/**
 * TODO: Insert description here.
 * 
 * @author Daniel Scheffknecht
 * @date May 4, 2015
 */
public interface IControllerFacade {
	static IControllerFacade getInstance() {
		return null;
	}
	INewAppointmentController getNewAppointmentController();
}
