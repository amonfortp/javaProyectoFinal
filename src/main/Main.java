/**
 * 
 */
package main;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controlador.controladorPrincipal;
import modeloBBDD.Modelo;
import vista.JFramePrincipal;

/**
 * @author amonfortp1
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Modelo modelo = new Modelo();
				JFramePrincipal vista = new JFramePrincipal();
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							SwingUtilities.updateComponentTreeUI(vista);
						}
					}

					new controladorPrincipal(vista, modelo).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
