/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeloBBDD.AlumnoBBDD;
import modeloBBDD.Modelo;
import vista.JIReservar;

/**
 * Controlador del JIReservar donde se controlara las acciones de los botones y
 * la gestion de cada reserva
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorReservar implements ActionListener {

	private JIReservar JIR;
	private Modelo modelo;
	private AlumnoBBDD a;

	/**
	 * Constructor del controladorReservar
	 * 
	 * @param reservar JInternalFrame de las reservas
	 * @param modelo   es de la clase Modelo
	 * @param a        objeto AlumnoBBDD
	 */
	public controladorReservar(JIReservar reservar, Modelo modelo, AlumnoBBDD a) {
		super();
		this.JIR = reservar;
		this.modelo = modelo;
		this.a = a;
		Iniciar();
	}

	private void Iniciar() {
		JIR.btnCerrar.setActionCommand("cerrar");
		JIR.btnEliminarReserva.setActionCommand("eliminar reserva");
		JIR.btnReservar.setActionCommand("reservar");

		JIR.btnCerrar.addActionListener(this);
		JIR.btnEliminarReserva.addActionListener(this);
		JIR.btnReservar.addActionListener(this);
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
			JIR.dispose();
		} else if (comand.equals("eliminar reserva")) {

		} else if (comand.equals("reservar")) {

		}

	}

}
