/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.Map;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import modeloBBDD.AlumnoBBDD;
import modeloBBDD.Modelo;
import vista.JIReservar;

/**
 * Controlador del JIReservar donde se controlara las acciones de los botones y
 * la gestion de cada reserva
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorReservas implements ActionListener, CalendarListener {

	private JIReservar JIR;
	private Modelo modelo;
	private AlumnoBBDD a;
	private Map<LocalDate, LinkedHashSet<LocalTime>> reservas;

	/**
	 * Constructor del controladorReservar
	 * 
	 * @param reservar JInternalFrame de las reservas
	 * @param modelo   es de la clase Modelo
	 * @param a        objeto AlumnoBBDD
	 */
	public controladorReservas(JIReservar reservar, Modelo modelo, AlumnoBBDD a,
			Map<LocalDate, LinkedHashSet<LocalTime>> dh) {
		super();
		this.JIR = reservar;
		this.modelo = modelo;
		this.a = a;
		reservas = dh;
		Iniciar();
	}

	private void Iniciar() {
		JIR.btnCerrar.setActionCommand("cerrar");
		JIR.btnEliminarReserva.setActionCommand("eliminar reserva");
		JIR.btnReservar.setActionCommand("reservar");

		JIR.btnCerrar.addActionListener(this);
		JIR.btnEliminarReserva.addActionListener(this);
		JIR.btnReservar.addActionListener(this);

		JIR.calendarioReservas.addCalendarListener(this);

		if (modelo.obtenerReserva(a.getEmail()) == null) {
			JIR.textFieldFecha.setText("No tienen aun ninguna cita");
		} else {
			JIR.textFieldFecha.setText(modelo.obtenerReserva(a.getEmail()));
		}

	}

	/**
	 * Hace visible el JInternalFrame de las reservas
	 */
	public void start() {
		JIR.setVisible(true);
	}

	private void cargarHoras(LocalDate dia) {
		Iterator<LocalTime> dias = reservas.get(dia).iterator();
		JIR.comboBoxReservas.removeAllItems();
		while (dias.hasNext()) {
			JIR.comboBoxReservas.addItem(dias.next());
		}
	}

	private void reservar() {
		String email;
		LocalDate dia;
		LocalTime hora;

		email = a.getEmail();
		dia = JIR.calendarioReservas.getSelectedDate();
		hora = (LocalTime) JIR.comboBoxReservas.getSelectedItem();
	}

//	private void generarReportReserva(String email, String passwd) {
//
//		String reportUrl = "/reports/confirmacion.jasper";
//		InputStream reportFile = null;
//
//		reportFile = getClass().getResourceAsStream(reportUrl);
//
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("password", passwd);
//		parametros.put("password", email);
//
//		try {
//			JasperPrint print = JasperFillManager.fillReport(reportFile, parametros);
//			JasperExportManager.exportReportToPdfFile(print, "confirmacion.pdf");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String comand = e.getActionCommand();

		if (comand.equals("cerrar")) {
			JIR.dispose();
		} else if (comand.equals("eliminar reserva")) {

		} else if (comand.equals("reservar")) {
			reservar();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.lgooddatepicker.optionalusertools.CalendarListener#
	 * selectedDateChanged(com.github.lgooddatepicker.zinternaltools.
	 * CalendarSelectionEvent)
	 */
	@Override
	public void selectedDateChanged(CalendarSelectionEvent e) {
		// TODO Auto-generated method stub

		cargarHoras(e.getNewDate());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.lgooddatepicker.optionalusertools.CalendarListener#
	 * yearMonthChanged(com.github.lgooddatepicker.zinternaltools.
	 * YearMonthChangeEvent)
	 */
	@Override
	public void yearMonthChanged(YearMonthChangeEvent e) {
		// TODO Auto-generated method stub

	}

}
