/**
 * 
 */
package controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import modeloBBDD.Modelo;
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
	public void iniciar() {
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
		view.btnConfiguracin.setActionCommand("configuracion");
		view.btnSalir.setActionCommand("salir");

		view.btnLogin.addActionListener(this);
		view.btnReservas.addActionListener(this);
		view.btnPeriodos.addActionListener(this);
		view.btnConfiguracin.addActionListener(this);
		view.btnSalir.addActionListener(this);
	}

	/**
	 * 
	 * Hacer visible el JFrame
	 * 
	 */
	public void start() {
		view.setVisible(true);
	}

	/**
	 * 
	 * Cargar JDLogin
	 *
	 */
	public void cargarLogin() {
		login = new JDLogin();
		controladorLogin cl = new controladorLogin(login, modelo);
		cl.start();

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

		}
	}

}
