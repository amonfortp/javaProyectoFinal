/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeloBBDD.Modelo;
import vista.JDLogin;

/**
 * Controlador del JDLogin donde gestionaremos el inicio de sesion del
 * administrador o el alumno
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 * 
 */
public class controladorLogin implements ActionListener {

	private JDLogin login;
	private Modelo modelo;

	/**
	 * @param login  es el JDLogin
	 * @param modelo es una clase Modelo
	 */
	public controladorLogin(JDLogin login, Modelo modelo) {
		super();
		this.login = login;
		this.modelo = modelo;
		Iniciar();
	}

	/**
	 * 
	 * Metodo para asignar una accion a todas las opciones del JDLogin que
	 * necesitemos
	 * 
	 */
	public void Iniciar() {
		login.btnAceptar.setActionCommand("aceptar");
		login.btnCancelar.setActionCommand("cancelar");

		login.btnAceptar.addActionListener(this);
		login.btnCancelar.addActionListener(this);
	}

	/**
	 * 
	 * Hacer visible el JDialog
	 * 
	 */
	public void start() {
		login.setVisible(true);
	}

	/**
	 * 
	 * Este metodo consiste en asignar una accion a todas las opciones del JDLogin
	 *
	 * @param e es una ActionEvent
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comand = e.getActionCommand();

		if (comand.equals("aceptar")) {

		} else if (comand.equals("cancelar")) {

		}
	}

}
