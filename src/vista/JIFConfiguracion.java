package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import net.miginfocom.swing.MigLayout;

public class JIFConfiguracion extends JInternalFrame {
	public JTextField textFieldHost;
	public JTextField textFieldPort;
	public JTextField textFieldData;
	public JTextField textFieldUser;
	public JPasswordField passwordField;
	public JButton btnCancelar;
	public JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFConfiguracion frame = new JIFConfiguracion();
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
	public JIFConfiguracion() {
		setTitle("Configuracion");
		setClosable(true);
		setBounds(100, 100, 359, 354);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 609,
										Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_1.setLayout(new MigLayout("", "[298px]", "[15px][19px][15px][19px][15px][19px][15px][19px][15px][19px]"));

		JLabel lblHost = new JLabel("Host");
		panel_1.add(lblHost, "cell 0 0,growx,aligny top");

		textFieldHost = new JTextField();
		panel_1.add(textFieldHost, "cell 0 1,growx,aligny top");
		textFieldHost.setColumns(10);

		JLabel lblPort = new JLabel("Port");
		panel_1.add(lblPort, "cell 0 2,growx,aligny top");

		textFieldPort = new JTextField();
		panel_1.add(textFieldPort, "cell 0 3,growx,aligny top");
		textFieldPort.setColumns(10);

		JLabel lblDatabases = new JLabel("Databases");
		panel_1.add(lblDatabases, "cell 0 4,growx,aligny top");

		textFieldData = new JTextField();
		panel_1.add(textFieldData, "cell 0 5,growx,aligny top");
		textFieldData.setColumns(10);

		JLabel lblUser = new JLabel("User");
		panel_1.add(lblUser, "cell 0 6,growx,aligny top");

		textFieldUser = new JTextField();
		panel_1.add(textFieldUser, "cell 0 7,growx,aligny top");
		textFieldUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		panel_1.add(lblPassword, "cell 0 8,growx,aligny top");

		passwordField = new JPasswordField();
		panel_1.add(passwordField, "cell 0 9,growx,aligny top");

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		panel.add(btnAceptar);
		getContentPane().setLayout(groupLayout);

	}
}
