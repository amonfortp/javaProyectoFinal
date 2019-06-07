/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modeloBBDD.Modelo;
import vista.JIFPeriodos;

/**
 * Controlador del JIFPeriodos donde se controlara las acciones de los botones y
 * la gestion de cada periodo ademas de visualizar reservas y modificar mensajes
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorPeriodos implements ActionListener, MouseListener {

	JIFPeriodos JIP;
	Modelo modelo;

	/**
	 * @param periodos JInternalFrame del administrador
	 * @param modelo
	 */
	public controladorPeriodos(JIFPeriodos periodos, Modelo modelo) {
		super();
		this.JIP = periodos;
		this.modelo = modelo;
		iniciar();
	}

	private void iniciar() {
		JIP.btnActualizar.setActionCommand("actualizar mensaje");
		JIP.btnCancelar.setActionCommand("cerrar");
		JIP.btnCrear.setActionCommand("crear periodo");
		JIP.comboBoxCurso2.setActionCommand("mostrar periodos");
		JIP.comboBoxPeriodos.setActionCommand("mostrar reservas");
		JIP.comboBoxMensajes.setActionCommand("mostrar mensaje");
		JIP.btnEnabled.setActionCommand("activar periodo");

		JIP.btnActualizar.addActionListener(this);
		JIP.btnCancelar.addActionListener(this);
		JIP.btnCrear.addActionListener(this);
		JIP.comboBoxCurso2.addActionListener(this);
		JIP.comboBoxPeriodos.addActionListener(this);
		JIP.comboBoxMensajes.addActionListener(this);
		JIP.btnEnabled.addActionListener(this);

	}

	/**
	 * 
	 * Metodo para hacer visible el JinternalFrame de periodos
	 */
	public void start() {
		JIP.setVisible(true);
	}

	private void crearPeriodo() {

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

		if (comand.equals("cerrar")) {
			JIP.dispose();
		} else if (comand.equals("crear periodo")) {

		} else if (comand.equals("activar periodo")) {

		} else if (comand.equals("mostrar periodos")) {

		} else if (comand.equals("mostrar reservas")) {

		} else if (comand.equals("mostrar mensaje")) {

		} else if (comand.equals("actualizar mensaje")) {

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
