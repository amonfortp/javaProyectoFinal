package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPasswordField;

public class JDLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JButton btnAceptar;
	public JButton btnCancelar;
	public JTextField textField;
	public JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDLogin dialog = new JDLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDLogin() {
		setModal(true);
		setBounds(100, 100, 535, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(new ImageIcon(JDLogin.class.getResource("/images/loginPanel.png")));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new MigLayout("", "[180.00,grow]", "[19px][][][][][]"));
			{
				JLabel lblUsuario = new JLabel("Usuario");
				panel.add(lblUsuario, "cell 0 1,alignx left,aligny center");
			}
			{
				textField = new JTextField();
				panel.add(textField, "cell 0 2,growx,aligny top");
				textField.setColumns(10);
			}
			{
				JLabel lblContrasea = new JLabel("Contraseña");
				panel.add(lblContrasea, "cell 0 4,alignx left");
			}
			{
				passwordField = new JPasswordField();
				panel.add(passwordField, "cell 0 5,growx");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
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
