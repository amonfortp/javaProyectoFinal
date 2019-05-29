/**
 * 
 */
package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.JComboBox;

/**
 * Explicacion de la clase
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class JIReservar extends JInternalFrame {
	public JButton btnReservar;
	public JTextField textFieldFecha;
	public CalendarPanel calendarioReservas;
	public JButton btnEliminarReserva;
	public JButton btnCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIReservar frame = new JIReservar();
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
	public JIReservar() {
		setClosable(true);
		setBounds(100, 100, 366, 557);

		JPanel panel_1 = new JPanel();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
						.addContainerGap(129, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		calendarioReservas = new CalendarPanel();

		btnReservar = new JButton("Reservar");

		JLabel lblTuReserva = new JLabel("Tu reserva");

		textFieldFecha = new JTextField();
		textFieldFecha.setEnabled(false);
		textFieldFecha.setColumns(10);

		btnEliminarReserva = new JButton("Eliminar Reserva");
		
		JComboBox comboBoxReservas = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTuReserva)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldFecha, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(91)
					.addComponent(btnEliminarReserva)
					.addContainerGap(97, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(comboBoxReservas, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(btnReservar)
							.addGap(54))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(calendarioReservas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(15, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(calendarioReservas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReservar)
						.addComponent(comboBoxReservas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTuReserva)
						.addComponent(textFieldFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(btnEliminarReserva)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		btnCerrar = new JButton("Cerrar");
		panel_1.add(btnCerrar);
		getContentPane().setLayout(groupLayout);

	}
}
