/**
 * 
 */
package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateHighlightPolicy;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;

import modeloBBDD.Mensaje;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.ListSelectionModel;

/**
 * Explicacion de la clase
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class JIFPeriodos extends JInternalFrame {
	public static HashSet<LocalDate> reservas;
	public JButton btnCrear;
	public JButton btnCancelar;
	public JComboBox comboBoxCurso1;
	public JComboBox comboBoxPeriodos;
	public JComboBox<Mensaje> comboBoxMensajes;
	public JTextPane textPaneMensajes;
	public JButton btnActualizar;
	public JComboBox comboBoxCurso2;
	public JButton btnEnabled;
	public DateTimePicker dateTimePickerInicio;
	public DateTimePicker dateTimePickerFinal;
	public JComboBox<LocalTime> comboBoxTiempo;
	public JTable tablePeriodos;
	public JTable tableReservas;

	/**
	 * Create the frame.
	 */
	public JIFPeriodos(HashSet<LocalDate> reservas) {
		this.reservas = reservas;

		setClosable(true);
		setBounds(100, 100, 595, 637);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		panel_1.add(tabbedPane);

		JPanel Periodos = new JPanel();
		Periodos.setToolTipText("");
		tabbedPane.addTab("Periodos", null, Periodos, null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear Periodo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panel_3 = new JPanel();

		JLabel lblNewLabel = new JLabel("Cursos");

		comboBoxCurso2 = new JComboBox();

		btnEnabled = new JButton("Des/Activar");
		GroupLayout gl_Periodos = new GroupLayout(Periodos);
		gl_Periodos.setHorizontalGroup(gl_Periodos.createParallelGroup(Alignment.TRAILING).addGroup(gl_Periodos
				.createSequentialGroup()
				.addGroup(gl_Periodos.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_Periodos
						.createSequentialGroup().addContainerGap()
						.addGroup(gl_Periodos.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 534,
										Short.MAX_VALUE)
								.addComponent(btnEnabled).addComponent(panel_3, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)))
						.addGroup(gl_Periodos.createSequentialGroup().addGap(12).addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBoxCurso2,
										GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_Periodos.setVerticalGroup(gl_Periodos.createParallelGroup(Alignment.TRAILING).addGroup(gl_Periodos
				.createSequentialGroup().addContainerGap()
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(gl_Periodos.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxCurso2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnEnabled).addContainerGap()));
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		tablePeriodos = new JTable();
		tablePeriodos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tablePeriodos);

		JLabel lblDiaInicioReserva = new JLabel("Dia Inicio Reserva");

		JLabel lblDiaFinalReserva = new JLabel("Dia Final Reserva");

		JLabel lblHoraInicio = new JLabel("Hora Inicio");

		JLabel lblHoraFinal = new JLabel("Hora Final");

		comboBoxCurso1 = new JComboBox();

		JLabel lblCurso = new JLabel("Curso (año)");

		btnCrear = new JButton("Crear");

		JLabel lblTiempo = new JLabel("Tiempo (minutos)");

		comboBoxTiempo = new JComboBox<LocalTime>();

		DatePickerSettings ds1 = new DatePickerSettings();
		ds1.setAllowKeyboardEditing(false);
		TimePickerSettings ts1 = new TimePickerSettings();
		ts1.setAllowKeyboardEditing(false);
		ts1.initialTime = LocalTime.of(7, 0);
		dateTimePickerInicio = new DateTimePicker(ds1, ts1);
		ds1.setVetoPolicy(new SampleDateVetoPolicy());
		ds1.setHighlightPolicy(new SampleHighlightPolicy());
		ts1.setVetoPolicy(new SampleTimeVetoPolicy());

		DatePickerSettings ds2 = new DatePickerSettings();
		ds2.setAllowKeyboardEditing(false);
		TimePickerSettings ts2 = new TimePickerSettings();
		ts2.setAllowKeyboardEditing(false);
		ts2.initialTime = LocalTime.of(18, 0);
		dateTimePickerFinal = new DateTimePicker(ds2, ts2);
		ds2.setVetoPolicy(new SampleDateVetoPolicy());
		ds2.setHighlightPolicy(new SampleHighlightPolicy());
		ts2.setVetoPolicy(new SampleTimeVetoPolicy());

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(dateTimePickerInicio, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(dateTimePickerFinal, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(31))
						.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblDiaInicioReserva)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblHoraInicio).addGap(42))
						.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblDiaFinalReserva)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblHoraFinal).addGap(44)))
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblCurso)
								.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
								.addComponent(btnCrear))
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxTiempo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxCurso1, 0, 119, Short.MAX_VALUE))
						.addComponent(lblTiempo))
				.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblDiaInicioReserva)
								.addComponent(lblCurso).addComponent(lblHoraInicio))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(dateTimePickerInicio, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxCurso1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblDiaFinalReserva)
								.addComponent(lblTiempo).addComponent(lblHoraFinal))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(dateTimePickerFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTiempo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnCrear))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		Periodos.setLayout(gl_Periodos);

		JPanel Reservas = new JPanel();
		tabbedPane.addTab("Reservas", null, Reservas, null);

		JLabel lblPeriodo = new JLabel("Periodo");

		comboBoxPeriodos = new JComboBox();

		JPanel panel_4 = new JPanel();
		GroupLayout gl_Reservas = new GroupLayout(Reservas);
		gl_Reservas.setHorizontalGroup(gl_Reservas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Reservas.createSequentialGroup().addContainerGap()
						.addGroup(gl_Reservas.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
								.addGroup(gl_Reservas.createSequentialGroup().addComponent(lblPeriodo)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBoxPeriodos,
												GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_Reservas.setVerticalGroup(gl_Reservas.createParallelGroup(Alignment.LEADING).addGroup(gl_Reservas
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_Reservas.createParallelGroup(Alignment.BASELINE).addComponent(lblPeriodo).addComponent(
						comboBoxPeriodos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE).addContainerGap()));
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);

		tableReservas = new JTable();
		scrollPane_1.setViewportView(tableReservas);
		Reservas.setLayout(gl_Reservas);

		JPanel Mensajes = new JPanel();
		tabbedPane.addTab("Mensajes", null, Mensajes, null);

		JLabel lblMensaje = new JLabel("Mensaje");

		comboBoxMensajes = new JComboBox<Mensaje>();

		textPaneMensajes = new JTextPane();

		btnActualizar = new JButton("Actualizar");
		GroupLayout gl_Mensajes = new GroupLayout(Mensajes);
		gl_Mensajes.setHorizontalGroup(gl_Mensajes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Mensajes.createSequentialGroup().addContainerGap()
						.addGroup(gl_Mensajes.createParallelGroup(Alignment.LEADING)
								.addComponent(textPaneMensajes, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
								.addGroup(gl_Mensajes.createSequentialGroup().addComponent(lblMensaje)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBoxMensajes,
												GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnActualizar, Alignment.TRAILING))
						.addContainerGap()));
		gl_Mensajes.setVerticalGroup(gl_Mensajes.createParallelGroup(Alignment.LEADING).addGroup(gl_Mensajes
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_Mensajes.createParallelGroup(Alignment.BASELINE).addComponent(lblMensaje).addComponent(
						comboBoxMensajes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textPaneMensajes, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnActualizar).addContainerGap()));
		Mensajes.setLayout(gl_Mensajes);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		getContentPane().setLayout(groupLayout);

	}

	private static class SampleHighlightPolicy implements DateHighlightPolicy {

		/**
		 * getHighlightInformationOrNull, Implement this function to indicate if a date
		 * should be highlighted, and what highlighting details should be used for the
		 * highlighted date.
		 *
		 * If a date should be highlighted, then return an instance of
		 * HighlightInformation. If the date should not be highlighted, then return
		 * null.
		 *
		 * You may (optionally) fill out the fields in the HighlightInformation class to
		 * give any particular highlighted day a unique foreground color, background
		 * color, or tooltip text. If the color fields are null, then the default
		 * highlighting colors will be used. If the tooltip field is null (or empty),
		 * then no tooltip will be displayed.
		 *
		 * Dates that are passed to this function will never be null.
		 */
		@Override
		public HighlightInformation getHighlightInformationOrNull(LocalDate date) {
			// Color rojo para los dias dentro de un periodo
			if (reservas.contains(date)) {
				return new HighlightInformation(Color.red, null, "Este dia pertenece a un periodo ¡CUIDADO!");
			}

			return null;
		}
	}

	private static class SampleTimeVetoPolicy implements TimeVetoPolicy {

		/**
		 * isTimeAllowed, Return true if a time should be allowed, or false if a time
		 * should be vetoed.
		 */
		@Override
		public boolean isTimeAllowed(LocalTime time) {
			// Solo se permite la hora desde las 7:00 hasta las 18:00
			return PickerUtilities.isLocalTimeInRange(time, LocalTime.of(7, 00), LocalTime.of(18, 00), true);
		}
	}

	private static class SampleDateVetoPolicy implements DateVetoPolicy {

		/**
		 * isDateAllowed, Return true if a date should be allowed, or false if a date
		 * should be vetoed.
		 */
		@Override
		public boolean isDateAllowed(LocalDate date) {

			// Desactivar fin de semana
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				return false;
			}
			// Allow all other days.
			return true;
		}

	}
}
