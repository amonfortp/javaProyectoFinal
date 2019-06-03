/**
 * 
 */
package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.optionalusertools.DateHighlightPolicy;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;

import modeloBBDD.Modelo;

import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePickerSettings;

/**
 * Explicacion de la clase
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class JIReservar extends JInternalFrame {
	public static HashSet<LocalDate> reservas;

	public JButton btnReservar;
	public JTextField textFieldFecha;
	public JButton btnEliminarReserva;
	public JButton btnCerrar;
	public CalendarPanel calendarioReservas;
	public JComboBox<LocalTime> comboBoxReservas;

	/**
	 * Create the frame.
	 */
	public JIReservar(HashSet<LocalDate> reservas) {
		this.reservas = reservas;
		setClosable(true);
		setBounds(100, 100, 413, 580);

		JPanel panel_1 = new JPanel();

		JPanel panel = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE).addGap(4)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE)));

		comboBoxReservas = new JComboBox<LocalTime>();
		btnReservar = new JButton("Reservar");
		btnReservar.setEnabled(false);

		textFieldFecha = new JTextField();
		textFieldFecha.setEnabled(false);
		textFieldFecha.setColumns(10);

		JLabel lblTuReserva = new JLabel("Tu reserva");

		btnEliminarReserva = new JButton("Eliminar Reserva");
		btnEliminarReserva.setEnabled(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(56)
						.addComponent(comboBoxReservas, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(37).addComponent(btnReservar).addContainerGap(85, Short.MAX_VALUE))
				.addGroup(
						gl_panel_2.createSequentialGroup().addGap(35).addComponent(lblTuReserva)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textFieldFecha, GroupLayout.PREFERRED_SIZE, 248,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(42, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup().addContainerGap(140, Short.MAX_VALUE)
						.addComponent(btnEliminarReserva).addGap(136)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxReservas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReservar))
				.addGap(18)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblTuReserva).addComponent(
						textFieldFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE).addComponent(btnEliminarReserva)
				.addContainerGap()));
		panel_2.setLayout(gl_panel_2);

		DatePickerSettings ds = new DatePickerSettings();
		calendarioReservas = new CalendarPanel(ds);
		ds.setVetoPolicy(new SampleDateVetoPolicy());

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel
						.createSequentialGroup().addContainerGap(50, Short.MAX_VALUE).addComponent(calendarioReservas,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(48)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel
						.createSequentialGroup().addContainerGap().addComponent(calendarioReservas,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(35, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		btnCerrar = new JButton("Cerrar");
		panel_1.add(btnCerrar);
		getContentPane().setLayout(groupLayout);

	}

	private static class SampleDateVetoPolicy implements DateVetoPolicy {

		/**
		 * isDateAllowed, Return true if a date should be allowed, or false if a date
		 * should be vetoed.
		 */
		@Override
		public boolean isDateAllowed(LocalDate date) {

			return reservas.contains(date);

//			// Desactivar fin de semana
//			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
//				return false;
//			}
////			 Allow all other days.
//			return true;
		}

	}
}
