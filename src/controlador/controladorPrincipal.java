/**
 * 
 */
package controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import configuracion.ConfiguracionSegura;
import gmail.GmailTool;
import modeloBBDD.AlumnoBBDD;
import modeloBBDD.Modelo;
import modeloBBDD.Password;
import modeloLDAP.LDAP;
import modeloLDAP.Persona;
import vista.JDCrearCuenta;
import vista.JDLogin;
import vista.JFramePrincipal;
import vista.JIFAbout;
import vista.JIFConfiguracion;
import vista.JIFPeriodos;
import vista.JIFReservar;

/**
 * Controlador del JFramePrincipal donde gestionar las llamadas de el resto de
 * controladores y las acciones de los botones, etc.
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorPrincipal implements ActionListener {

	private JFramePrincipal view;
	private Modelo modelo;
	private JDLogin login;
	private JDCrearCuenta cuenta;
	private JIFReservar reserva;
	private JIFPeriodos periodo;
	private JIFConfiguracion configuracion;
	private JIFAbout about;
	private String email;

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

	private void iniciar() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		view.setTitle("Aplicacion Proyecto Final");
		view.setMaximumSize(dim);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Iniciar los botones del JFramePrincipal

		view.btnLogin.setActionCommand("login");
		view.btnReservas.setActionCommand("reservas");
		view.btnPeriodos.setActionCommand("periodos");
		view.btnConfiguracion.setActionCommand("configuracion");
		view.btnSalir.setActionCommand("salir");
		view.btnAbout.setActionCommand("about");

		view.btnLogin.addActionListener(this);
		view.btnReservas.addActionListener(this);
		view.btnPeriodos.addActionListener(this);
		view.btnConfiguracion.addActionListener(this);
		view.btnSalir.addActionListener(this);
		view.btnAbout.addActionListener(this);

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
		login.btnCrearCuenta.setActionCommand("crear cuenta");

		login.btnAceptar.addActionListener(this);
		login.btnCancelar.addActionListener(this);
		login.btnCrearCuenta.addActionListener(this);

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
			email = l;
		} else {
			JOptionPane.showMessageDialog(view, "No existe el usuario, comprueba login o contraseña", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			login.textFieldLogin.setText("");
			login.passwordField.setText("");
		}
	}

	private boolean comprobarLDAP(String login, String passwd) {
		boolean b = false;
		int i = 0;
		ArrayList<Persona> profesores = new ArrayList<Persona>();
		LDAP ldap = new LDAP();
		profesores = ldap.memberOf("Docente");

		if (ldap.autenticacionLDAP(login, passwd)) {
			while (i < profesores.size() && b == false) {
				if (profesores.get(i).getUid().compareTo(login) == 0) {
					if (ldap.search("cn=Docente,ou=groups", "memberUid=" + profesores.get(i).getUidNumber())) {
						b = true;
					}
				}
				i++;
			}
		}

		return b;

	}

	private boolean comprobarBBDD(String login, String passwd) {
		boolean b = false;

		if (modelo.autentificar(login, passwd)) {
			b = true;
		}

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

	private void cargarCrearCuenta() {
		cuenta = new JDCrearCuenta();
		cuenta.btnAceptar.setActionCommand("aceptar cuenta");
		cuenta.btnCancelar.setActionCommand("cancelar cuenta");

		cuenta.btnAceptar.addActionListener(this);
		cuenta.btnCancelar.addActionListener(this);

		cuenta.setVisible(true);
	}

	private void crearCuenta() {
		String email;
		String nombre;
		String apellido1;
		String apellido2;
		String password = crearContraseñas();

		email = cuenta.textFieldEmail.getText();
		nombre = cuenta.textFieldNombre.getText();
		apellido1 = cuenta.textFieldApellido1.getText();
		apellido2 = cuenta.textFieldApellido2.getText();

		if (comprobarCorreo(email) == true) {

			AlumnoBBDD a = new AlumnoBBDD(email, nombre, apellido1, apellido2);

			if (modelo.insertarAlumno(a, password)) {
				generarCorreoCuenta(a.getEmail(), password);
				cuenta.dispose();
				JOptionPane.showMessageDialog(view,
						"Se a creado la cuenta, revisa tu correo electronico, en el tendras la contraseña", "info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(view, "Hubo algun error al crear el usuario", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(view, "El correo no es valido", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String crearContraseñas() {
		Password p = new Password();

		return p.generarPassword(p.MAYUSCULAS + p.MINUSCULAS + p.NUMEROS + p.SIMBOLOS, 8);
	}

	private boolean comprobarCorreo(String email) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

		Matcher mather = pattern.matcher(email);

		return mather.find();
	}

	private void generarCorreoCuenta(String email, String passwd) {

		String mensaje = modelo.obtenerMensaje("mensajeUsuarioCreado");

		String to = email;

		String from = (new ConfiguracionSegura()).getMailFrom();
		String subject = "Confirmación cuenta";
		String body = "<h1>Información de la cuenta</h1>" + "<p>" + mensaje + "</p>" + "<p>Email: " + email + "</p>"
				+ "<p> Contraseña: " + passwd + "</p>";

		GmailTool.sendHtml(to, from, subject, body);
	}

	private void abrirReserva() {
		if (!estaAbierto(reserva)) {

			Map<LocalDate, TreeSet<LocalTime>> dh = modelo.obtenerDiasHoras(true);
			HashSet<LocalDate> reservas = new HashSet<LocalDate>();

			for (LocalDate dia : dh.keySet()) {
				reservas.add(dia);
			}

			reserva = new JIFReservar(reservas);
			AlumnoBBDD a = modelo.obtenerAlumno(email);
			controladorReservas cr = new controladorReservas(reserva, modelo, a, dh);
			view.desktopPane.add(reserva);
			cr.start();
		}
	}

	private void abrirConfiguracion() {
		// TODO Auto-generated method stub

		if (!estaAbierto(configuracion)) {
			configuracion = new JIFConfiguracion();
			controladorConf conf = new controladorConf(configuracion, modelo);
			view.desktopPane.add(configuracion);
			conf.start();
		}

	}

	private void abrirPeriodos() {
		if (!estaAbierto(periodo)) {
			Map<LocalDate, TreeSet<LocalTime>> dh = modelo.obtenerDiasHoras(false);
			HashSet<LocalDate> reservas = new HashSet<LocalDate>();

			for (LocalDate dia : dh.keySet()) {
				reservas.add(dia);
			}

			periodo = new JIFPeriodos(reservas);
			controladorPeriodos cp = new controladorPeriodos(periodo, modelo);
			view.desktopPane.add(periodo);
			cp.start();
		}
	}

	private void cerrarSesion() {
		int opcion = JOptionPane.YES_NO_OPTION;

		if (opcion == JOptionPane.YES_OPTION) {
			view.btnLogin.setVisible(true);
			view.btnSalir.setEnabled(false);
			view.btnReservas.setVisible(false);
			view.btnPeriodos.setVisible(false);
			view.btnConfiguracion.setVisible(false);
			view.btnReservas.setEnabled(false);
			view.btnPeriodos.setEnabled(false);
			view.btnConfiguracion.setEnabled(false);
		}
	}

	private void abrirAbout() {
		if (!estaAbierto(about)) {
			about = new JIFAbout();
			view.desktopPane.add(about);
			about.setVisible(true);
		}
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
		}

		// Alumno

		else if (comand.equals("reservas")) {
			abrirReserva();
		}

		// Docente

		else if (comand.equals("periodos")) {
			abrirPeriodos();
		} else if (comand.equals("configuracion")) {
			abrirConfiguracion();
		}

		// Cerrar sesion

		else if (comand.equals("salir")) {
			cerrarSesion();
		}

		// Login

		else if (comand.equals("aceptar login")) {
			comprobarInicio();
		} else if (comand.equals("cancelar login")) {
			login.dispose();
		} else if (comand.equals("crear cuenta")) {
			login.dispose();
			cargarCrearCuenta();
		} else if (comand.equals("aceptar cuenta")) {
			crearCuenta();
		} else if (comand.equals("cancelar cuenta")) {
			cuenta.dispose();
		} else if (comand.equals("about")) {
			abrirAbout();
		}
	}

}
