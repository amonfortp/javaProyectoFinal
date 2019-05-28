package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JDCrearCuenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textFieldEmail;
	public JTextField textFieldNombre;
	public JTextField textFieldApellido1;
	public JTextField textFieldApellido2;
	public JButton btnAceptar;
	public JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDCrearCuenta dialog = new JDCrearCuenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDCrearCuenta() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][155.00,grow]", "[][][][][][][][]"));
		{
			JLabel lblEmail = new JLabel("Email");
			contentPanel.add(lblEmail, "cell 1 0,alignx trailing");
		}
		{
			textFieldEmail = new JTextField();
			contentPanel.add(textFieldEmail, "cell 2 0,growx");
			textFieldEmail.setColumns(10);
		}
		{
			JLabel lblNombre = new JLabel("Nombre");
			contentPanel.add(lblNombre, "cell 1 2,alignx trailing");
		}
		{
			textFieldNombre = new JTextField();
			contentPanel.add(textFieldNombre, "cell 2 2,growx");
			textFieldNombre.setColumns(10);
		}
		{
			JLabel lblPrimerApellido = new JLabel("Primer Apellido");
			contentPanel.add(lblPrimerApellido, "cell 1 4,alignx trailing");
		}
		{
			textFieldApellido1 = new JTextField();
			contentPanel.add(textFieldApellido1, "cell 2 4,growx");
			textFieldApellido1.setColumns(10);
		}
		{
			JLabel lblSegundoApellido = new JLabel("Segundo Apellido");
			contentPanel.add(lblSegundoApellido, "cell 1 6,alignx trailing");
		}
		{
			textFieldApellido2 = new JTextField();
			contentPanel.add(textFieldApellido2, "cell 2 6,growx");
			textFieldApellido2.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("OK");
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCancelar = new JButton("Cancel");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
