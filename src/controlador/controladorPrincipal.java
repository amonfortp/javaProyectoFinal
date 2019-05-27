/**
 * 
 */
package controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import modeloBBDD.Modelo;
import modeloLDAP.LDAP;
import modeloLDAP.Persona;
import modeloLDAP.Profesor;
import vista.JDLogin;
import vista.JFramePrincipal;

/**
 * Controlador del JFramePrincipal donde gestionar las llamadas de el resto de
 * controladores y las acciones ded los botones, etc.
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorPrincipal implements ActionListener {

	private JFramePrincipal view;
	private Modelo modelo;
	private JDLogin login;

	/**
	 * Constructor del controlador principal
	 * 
	 * @param view   es el JFRAME JFramePrincipal
	 * @param modelo es la clase Modelo
	 */
	public controladorPrincipal(JFramePrincipal view, Modelo modelo) {
		super();
		this.view = view;
		this.modelo = modelo;
		iniciar();
	}

	/**
	 * 
	 * Metodo para asignar una accion a todas las opciones del JFramePrincipal que
	 * necesitemos
	 * 
	 */
	private void iniciar() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		view.setTitle("Aplicacion Proyecto Final");
		view.setMaximumSize(dim);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);

		/*
		 * Iniciar los botones del JFramePrincipal
		 */

		view.btnLogin.setActionCommand("login");
		view.btnReservas.setActionCommand("reservas");
		view.btnPeriodos.setActionCommand("periodos");
		view.btnConfiguracion.setActionCommand("configuracion");
		view.btnSalir.setActionCommand("salir");

		view.btnLogin.addActionListener(this);
		view.btnReservas.addActionListener(this);
		view.btnPeriodos.addActionListener(this);
		view.btnConfiguracion.addActionListener(this);
		view.btnSalir.addActionListener(this);

		view.btnReservas.setVisible(false);
		view.btnPeriodos.setVisible(false);
		view.btnConfiguracion.setVisible(false);
		view.btnSalir.setEnabled(false);
	}

	/**
	 * 
	 * Hacer visible el JFrame
	 * 
	 */
	public void start() {
		view.setVisible(true);
	}

	private void cargarLogin() {
		login = new JDLogin();
		login.btnAceptar.setActionCommand("aceptar login");
		login.btnCancelar.setActionCommand("cancelar login");

		login.btnAceptar.addActionListener(this);
		login.btnCancelar.addActionListener(this);

		login.setVisible(true);
	}

	private void comprobarInicio() {
		String l = login.textFieldLogin.getText();
		String p = String.valueOf(login.passwordField.getPassword());

		if (comprobarLDAP(l, p)) {
			view.btnPeriodos.setVisible(true);
			view.btnConfiguracion.setVisible(true);
			view.btnPeriodos.setEnabled(true);
			view.btnConfiguracion.setEnabled(true);
			view.btnSalir.setEnabled(true);
			view.btnLogin.setVisible(false);
			login.dispose();
		} else if (comprobarBBDD(l, p)) {
			view.btnReservas.setVisible(true);
			view.btnReservas.setEnabled(true);
			view.btnSalir.setEnabled(true);
			view.btnLogin.setVisible(false);
			login.dispose();
		} else {
			JOptionPane.showMessageDialog(view, "No existe el usuario, comprueba login o contraseña", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			login.textFieldLogin.setText("");
			login.passwordField.setText("");
		}
	}

	private boolean comprobarLDAP(String login, String passwd) {
		boolean b = false;
		ArrayList<Persona> profesores = new ArrayList<Persona>();
		LDAP ldap = new LDAP();
		profesores = ldap.memberOf("Docente");

		if (ldap.autenticacionLDAP(login, passwd)) {
			for (int i = 0; i < profesores.size(); i++) {
				if (ldap.obtenerUsuarioLDAPByUID(profesores.get(i))) {
					b = true;
				}
			}
		}

		return b;
	}

	private boolean comprobarBBDD(String login, String passwd) {
		boolean b = false;

		return b;
	}

	private boolean estaAbierto(JInternalFrame jif) {
		boolean abierto = false;
		JInternalFrame[] internalFrames = view.desktopPane.getAllFrames();

		for (JInternalFrame ji : internalFrames) {
			if (ji == jif) {
				abierto = true;
			}
		}

		return abierto;
	}

	/**
	 * 
	 * Este metodo consiste en asignar una accion a todas las opciones del
	 * JFramePrincipal
	 *
	 * @param e es un ActionEvent
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comand = e.getActionCommand();

		if (comand.equals("login")) {
			cargarLogin();
		} else if (comand.equals("reservas")) {

		} else if (comand.equals("periodos")) {

		} else if (comand.equals("configuracion")) {

		} else if (comand.equals("salir")) {

		} else if (comand.equals("aceptar login")) {
			comprobarInicio();
		} else if (comand.equals("cancelar login")) {
			login.dispose();
		}
	}

}
