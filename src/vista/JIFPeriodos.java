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
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

/**
 * Explicacion de la clase
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class JIFPeriodos extends JInternalFrame {
	public DatePicker datePickerFinal;
	public DatePicker datePickerInicio;
	public JTable tablePeriodos;
	public JTable tableReservas;
	public TimePicker timePickerFinal;
	public TimePicker timePickerInicio;
	public JButton btnCrear;
	public JButton btnCancelar;
	public JComboBox comboBoxCurso1;
	public JComboBox comboBoxPeriodos;
	public JComboBox comboBoxMensajes;
	public JTextPane textPane;
	public JButton btnActualizar;
	public JComboBox comboBoxCurso2;
	public JButton btnEnabled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFPeriodos frame = new JIFPeriodos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JIFPeriodos() {
		setClosable(true);
		setBounds(100, 100, 595, 522);

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

		JPanel panel_3 = new JPanel();

		JLabel lblNewLabel = new JLabel("Cursos");

		comboBoxCurso2 = new JComboBox();

		btnEnabled = new JButton("Des/Activar");
		GroupLayout gl_Periodos = new GroupLayout(Periodos);
		gl_Periodos.setHorizontalGroup(gl_Periodos.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Periodos.createSequentialGroup().addContainerGap()
						.addGroup(gl_Periodos.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
								.addGroup(gl_Periodos.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBoxCurso2, GroupLayout.PREFERRED_SIZE, 117,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
										.addComponent(btnEnabled)))
						.addContainerGap()));
		gl_Periodos.setVerticalGroup(gl_Periodos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Periodos.createSequentialGroup().addContainerGap()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
						.addGroup(gl_Periodos.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(comboBoxCurso2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEnabled))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		tablePeriodos = new JTable();
		panel_3.add(tablePeriodos, BorderLayout.NORTH);

		JLabel lblDiaInicioReserva = new JLabel("Dia Inicio Reserva");

		datePickerInicio = new DatePicker();

		JLabel lblDiaFinalReserva = new JLabel("Dia Final Reserva");

		datePickerFinal = new DatePicker();

		timePickerInicio = new TimePicker();

		JLabel lblHoraInicio = new JLabel("Hora Inicio");

		JLabel lblHoraFinal = new JLabel("Hora Final");

		timePickerFinal = new TimePicker();

		comboBoxCurso1 = new JComboBox();

		JLabel lblCurso = new JLabel("Curso");

		btnCrear = new JButton("Crear");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap().addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(datePickerFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(timePickerFinal, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(datePickerInicio, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDiaInicioReserva).addComponent(lblDiaFinalReserva))
								.addGap(18)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(lblHoraFinal)
										.addGroup(gl_panel_2.createSequentialGroup()
												.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
														.addComponent(timePickerInicio, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblHoraInicio))
												.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_2.createSequentialGroup().addGap(71)
																.addComponent(lblCurso))
														.addGroup(gl_panel_2.createSequentialGroup().addGap(34)
																.addGroup(gl_panel_2
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(btnCrear)
																		.addComponent(comboBoxCurso1,
																				GroupLayout.PREFERRED_SIZE, 130,
																				GroupLayout.PREFERRED_SIZE))))))))
				.addContainerGap(48, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblDiaInicioReserva)
						.addComponent(lblHoraInicio).addComponent(lblCurso))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(datePickerInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(timePickerInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxCurso1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblDiaFinalReserva)
						.addComponent(lblHoraFinal))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(datePickerFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(timePickerFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrear))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		Periodos.setLayout(gl_Periodos);

		JPanel Reservas = new JPanel();
		tabbedPane.addTab("Reservas", null, Reservas, null);

		JLabel lblCurso_1 = new JLabel("Curso");

		comboBoxPeriodos = new JComboBox();

		JPanel panel_4 = new JPanel();
		GroupLayout gl_Reservas = new GroupLayout(Reservas);
		gl_Reservas.setHorizontalGroup(gl_Reservas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Reservas.createSequentialGroup().addContainerGap()
						.addGroup(gl_Reservas.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
								.addGroup(gl_Reservas.createSequentialGroup().addComponent(lblCurso_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBoxPeriodos,
												GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_Reservas.setVerticalGroup(gl_Reservas.createParallelGroup(Alignment.LEADING).addGroup(gl_Reservas
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_Reservas.createParallelGroup(Alignment.BASELINE).addComponent(lblCurso_1).addComponent(
						comboBoxPeriodos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE).addContainerGap()));
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);

		tableReservas = new JTable();
		panel_4.add(tableReservas, BorderLayout.NORTH);
		Reservas.setLayout(gl_Reservas);

		JPanel Mensajes = new JPanel();
		tabbedPane.addTab("Mensajes", null, Mensajes, null);

		JLabel lblMensaje = new JLabel("Mensaje");

		comboBoxMensajes = new JComboBox();

		textPane = new JTextPane();

		btnActualizar = new JButton("Actualizar");
		GroupLayout gl_Mensajes = new GroupLayout(Mensajes);
		gl_Mensajes.setHorizontalGroup(gl_Mensajes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Mensajes.createSequentialGroup().addContainerGap()
						.addGroup(gl_Mensajes.createParallelGroup(Alignment.LEADING)
								.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
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
				.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnActualizar).addContainerGap()));
		Mensajes.setLayout(gl_Mensajes);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		getContentPane().setLayout(groupLayout);

	}
}
