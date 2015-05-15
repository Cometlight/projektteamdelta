package at.itb13.teamD.application.interfaces;

/**
 * This Interface defines the used methods of the ControllerFacade.
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
