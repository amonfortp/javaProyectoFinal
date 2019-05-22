/**
 * 
 */
package controlador;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import modeloBBDD.Modelo;
import vista.JFramePrincipal;

/**
 * Explicacion de la clase
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 * @param <E> (Especificar parametros)
 */
public class controladorPrincipal {

	private JFramePrincipal view;
	private Modelo modelo;

	/**
	 * Constructor del controlador principal
	 * 
	 * @param view
	 * @param modelo
	 */
	public controladorPrincipal(JFramePrincipal view, Modelo modelo) {
		super();
		this.view = view;
		this.modelo = modelo;
	}

	/**
	 * 
	 * Metodo para asignar una accion a todas las opciones del JFramePrincipal que
	 * necesitemos
	 * 
	 */
	public void iniciar() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	}

	/**
	 * 
	 * Hacer visible el JFrame
	 * 
	 */
	public void start() {
		view.setVisible(true);
	}

}
