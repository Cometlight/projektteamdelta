package at.itb13.oculus.presentation.gwt.client.appointmentChoice.view;

import java.util.List;

import at.itb13.oculus.application.ControllerFacade;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceService;
import at.itb13.oculus.presentation.gwt.client.appointmentChoice.rpc.AppointmentChoiceServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewService;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.rpc.AppointmentOverviewServiceAsync;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverview;
import at.itb13.oculus.presentation.gwt.client.appointmentOverview.view.AppointmentOverviewResources;
import at.itb13.oculus.presentation.gwt.shared.CalendarEvent;
import at.itb13.oculus.presentation.gwt.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

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
	@UiField
	Label datecell1;
	@UiField
	Label datecell2;
	@UiField
	Label datecell3;
	@UiField
	Label doctorcell1;
	@UiField
	Label doctorcell2;
	@UiField
	Label doctorcell3;
	@UiField
	Label typecell1;
	@UiField
	Label typecell2;
	@UiField
	Label typecell3;
	@UiField
	Label reasoncell1;
	@UiField
	Label reasoncell2;
	@UiField
	Label reasoncell3;
	@UiField
	TableRowElement firstRow;
	
	@UiField
	HTMLPanel htmlPanel;

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
	//it should be look like followed:
	public AppointmentChoice(Patient patient, List<CalendarEvent> events) {
	
	//old:
//	public AppointmentChoice(Patient patient) {	
		this.res = GWT.create(AppointmentChoiceResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		_patient = patient;

		nameLabel.setText(_patient.getName());
		sinLabel.setText(_patient.getSin());
		doctorLabel.setText(_patient.getDoctor());

//		datecell1.setText(events.get(0).getDate());
//		doctorcell1.setText(events.get(0).getDoctor());
//		typecell1.setText(events.get(0).getType());
//		reasoncell1.setText(events.get(0).getReason());
//		datecell2.setText(events.get(1).getDate());
//		doctorcell2.setText(events.get(1).getDoctor());
//		typecell2.setText(events.get(1).getType());
//		reasoncell2.setText(events.get(1).getReason());
//		datecell3.setText(events.get(2).getDate());
//		doctorcell3.setText(events.get(2).getDoctor());
//		typecell3.setText(events.get(2).getType());
//		reasoncell3.setText(events.get(2).getReason());
		
	
		
		CellTable<CalendarEvent> table = new CellTable<>();
		
		 // Create name column.
	    TextColumn<CalendarEvent> dateColumn = new TextColumn<CalendarEvent>() {
	      @Override
	      public String getValue(CalendarEvent event) {
	        return event.getDate();
	      }
	    };
	    
	    // Create address column.
	    TextColumn<CalendarEvent> doctorColumn = new TextColumn<CalendarEvent>() {
	      @Override
	      public String getValue(CalendarEvent event) {
	        return event.getDoctor();
	      }
	    };
	    
	 // Add the columns.
	   
	    table.addColumn(dateColumn, "Date");
	    table.addColumn(doctorColumn, "Doctor");
	    
	 // Create a data provider.
	    ListDataProvider<CalendarEvent> dataProvider = new ListDataProvider<CalendarEvent>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(table);
	    
	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<CalendarEvent> list = dataProvider.getList();
	    for (CalendarEvent ev : events) {
	      list.add(ev);
	    }
	    
	    table.addDomHandler(new ClickHandler()
	    {

	        @Override
	        public void onClick(ClickEvent event)
	        {
	            // TODO Auto-generated method stub

	               CellTable<CalendarEvent> selectedcell = (CellTable<CalendarEvent>)  event.getSource();
	                System.out.println("  Current Selected Row : "+selectedcell.getKeyboardSelectedRow());
	                Window.alert("you have clicked");

	        }
	    }, ClickEvent.getType());


	    table.sinkEvents(Event.ONCLICK);
	
	    htmlPanel.add(table);
	}

	
	
	
	


}
