/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import configuracion.ConfiguracionSegura;
import gmail.GmailTool;
import modeloBBDD.AlumnoBBDD;
import modeloBBDD.Modelo;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import vista.JIFReservar;

/**
 * Controlador del JIFReservar donde se controlara las acciones de los botones y
 * la gestion de cada reserva
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorReservas implements ActionListener, CalendarListener {

	private JIFReservar JIR;
	private Modelo modelo;
	private AlumnoBBDD a;
	private LocalDate dia;
	private LocalTime hora;
	private Map<LocalDate, TreeSet<LocalTime>> reservas;

	/**
	 * Constructor del controladorReservar
	 * 
	 * @param reservar JInternalFrame de las reservas
	 * @param modelo   Es de la clase Modelo
	 * @param a        Objeto AlumnoBBDD
	 * @param dh       Map que contiene como key variables LocalDate y un TreeSet
	 *                 formado por LocalTime
	 */
	public controladorReservas(JIFReservar reservar, Modelo modelo, AlumnoBBDD a,
			Map<LocalDate, TreeSet<LocalTime>> dh) {
		super();
		this.JIR = reservar;
		this.modelo = modelo;
		this.a = a;
		reservas = dh;
		dia = null;
		hora = null;
		iniciar();
	}

	private void iniciar() {
		JIR.btnCerrar.setActionCommand("cerrar");
		JIR.btnEliminarReserva.setActionCommand("eliminar reserva");
		JIR.btnReservar.setActionCommand("reservar");

		JIR.btnCerrar.addActionListener(this);
		JIR.btnEliminarReserva.addActionListener(this);
		JIR.btnReservar.addActionListener(this);

		JIR.calendarioReservas.addCalendarListener(this);

		if (modelo.obtenerReserva(a.getEmail()) == null) {
			JIR.textFieldFecha.setText("No tienes aun ninguna cita");
		} else {
			JIR.textFieldFecha.setText(modelo.obtenerReserva(a.getEmail()));
			JIR.btnEliminarReserva.setEnabled(true);
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

		email = a.getEmail();
		dia = JIR.calendarioReservas.getSelectedDate();
		hora = (LocalTime) JIR.comboBoxReservas.getSelectedItem();

		if (modelo.reservar(email, dia, hora)) {
			reservas = modelo.obtenerDiasHoras(true);
			JOptionPane.showMessageDialog(null, "La reserva se realizo correctamente", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			JIR.textFieldFecha.setText(modelo.obtenerReserva(a.getEmail()));
			JIR.btnEliminarReserva.setEnabled(true);
			generarCorreoReserva();
		}
	}

	private void generarCorreoReserva() {

		String to = a.getEmail();

		generarReportReserva();

		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("Reserva.pdf"));

		String from = (new ConfiguracionSegura()).getMailFrom();
		String subject = "Confirmación Reserva";
		String body = "<h1>Informacion de la reserva</h1><p>A continuación se adjunta un documento "
				+ "con la información de la reserva</p>";

		GmailTool.sendHtmlWithAttachment(to, from, subject, body, files);
	}

	private void generarReportReserva() {

		String reportUrl = "/reports/Reserva.jasper";
		InputStream reportFile = null;

		String mensaje = modelo.obtenerMensaje("mensajeReservado");

		reportFile = getClass().getResourceAsStream(reportUrl);

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Message", mensaje);
		parametros.put("email", a.getEmail());
		try {
			JasperPrint print = JasperFillManager.fillReport(reportFile, parametros, modelo.conectar());
			System.out.println("PDF");
			JasperExportManager.exportReportToPdfFile(print, "Reserva.pdf");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void eliminar() {
		try {
			if (modelo.eliminarReserva(a.getEmail())) {
				JOptionPane.showMessageDialog(null, "Se a eliminado tu cita", "Info", JOptionPane.INFORMATION_MESSAGE);
				generarCorreoAnularReserva(dia, hora);
				dia = null;
				hora = null;
				JIR.textFieldFecha.setText("No tienes aun ninguna cita");
				JIR.btnEliminarReserva.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "No se a eliminado tu cita", "Info", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 1001) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (e.getErrorCode() == 1002) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void generarCorreoAnularReserva(LocalDate dia, LocalTime hora) {

		String mensaje = modelo.obtenerMensaje("mensajeReservadoAnulado");

		String to = a.getEmail();

		String from = (new ConfiguracionSegura()).getMailFrom();
		String subject = "Anulación Reserva";
		String body = "<h1>Anulación de la Reserva</h1><p>" + mensaje + "</p> <p>" + dia + "  " + hora + "</p>";

		GmailTool.sendHtml(to, from, subject, body);
	}

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
			eliminar();
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
		JIR.btnReservar.setEnabled(true);

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
