/**
 * 
 */
package modeloBBDD;

/**
 * @author amonfortp1
 *
 */
public class Modelo extends Database {

	public Modelo() {
		super();
	}

	public boolean autentificar(String login, String passwd) {
		boolean exito = false;

		if (contarAlumno(login, passwd) == 1) {
			exito = true;
		}

		return exito;
	}

}
