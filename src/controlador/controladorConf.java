/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import configuracion.ConfiguracionSegura;
import modeloBBDD.Modelo;
import vista.JIFConfiguracion;

/**
 * controlador de la vista de configuracion
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorConf implements ActionListener {

	private JIFConfiguracion configuracion;
	private Modelo modelo;

	/**
	 * @param conf   JIFConfiguracion
	 * @param modelo Modelo
	 */
	public controladorConf(JIFConfiguracion conf, Modelo modelo) {
		super();
		this.configuracion = conf;
		this.modelo = modelo;
		Iniciar();
	}

	private void Iniciar() {
		ConfiguracionSegura conf = new ConfiguracionSegura();

		configuracion.btnCancelar.setActionCommand("cerrar conf");
		configuracion.btnAceptar.setActionCommand("aceptar conf");
		configuracion.btnCancelar.addActionListener(this);
		configuracion.btnAceptar.addActionListener(this);

		configuracion.textFieldHost.setText(conf.getHost());
		configuracion.textFieldPort.setText(conf.getPort());
		configuracion.textFieldData.setText(conf.getDatabase());
		configuracion.textFieldUser.setText(conf.getUser());
		configuracion.passwordField.setText(conf.getPassword());
	}

	public void start() {
		configuracion.setVisible(true);
	}

	private void aceptarConfiguracion() {
		// TODO Auto-generated method stub
		ConfiguracionSegura conf = new ConfiguracionSegura();

		int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres guardar los cambios?", "Confirmacion",
				JOptionPane.YES_NO_OPTION);

		if (opcion == JOptionPane.YES_OPTION) {
			conf.setHost(configuracion.textFieldHost.getText());
			conf.setPort(configuracion.textFieldPort.getText());
			conf.setDatabase(configuracion.textFieldData.getText());
			conf.setUser(configuracion.textFieldUser.getText());
			conf.setPassword(configuracion.passwordField.getPassword().toString());
		}

		conf.guardar();
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

		if (comand.equals("aceptar conf")) {
			aceptarConfiguracion();
		} else if (comand.equals("cancelar conf")) {
			configuracion.dispose();
		}
	}

}
