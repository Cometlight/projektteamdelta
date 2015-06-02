package at.itb13.oculus.presentation.gwt.client.appointmentChoice.view;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceService;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverviewResources;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: Insert description here.
 * 
 * @author Caroline Meusburger
 * @since 01.06.2015
 */
public class AppointmentChoice extends Composite{

	private static AppointmentChoiceUiBinder uiBinder = GWT
			.create(AppointmentChoiceUiBinder.class);
	private final AppointmentChoiceServiceAsync appointmentChoiceAsyncService = GWT
			.create(AppointmentChoiceService.class);
	@UiTemplate("AppointmentChoice.ui.xml")
	interface AppointmentChoiceUiBinder extends UiBinder<Widget, AppointmentChoice> {
	}
	@UiField(provided = true)
	final AppointmentChoiceResources res;
	
	private Patient _patient;
	@UiField
	Label nameLabel;
	@UiField
	Label sinLabel;
	@UiField
	Label doctorLabel;	

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public AppointmentChoice(Patient patient) {
		
		this.res = GWT.create(AppointmentChoiceResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		_patient = patient;
		

		nameLabel.setText(_patient.getName());
		sinLabel.setText(_patient.getSin());
		doctorLabel.setText(_patient.getDoctor());

		
		AsyncCallback<String[]> callbackPatient = new AsyncCallback<String[]>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("AppointmentOverview - Failed to connect to server. Please try again in a few minutes, or contact the system administrator.");
			}

			@Override
			public void onSuccess(String[] result) {
				nameLabel.setText(result[0]);
				sinLabel.setText(result[1]);
				doctorLabel.setText(result[2]);
			}
			
		};
		// Musste auskommentieren, da es mir einen Error geworfen hat.
//		appointmentOverviewAsyncService.getPatientData(ControllerFacade.getInstance().getSelectedPatient(), callbackPatient);


	}


}
