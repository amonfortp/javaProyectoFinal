/**
 * 
 */
package modeloBBDD;

/**
 * 
 * Objeto para gestionar las contraseñas
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class Password {

	public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	public static final String NUMEROS = "123456789";
	public static final String SIMBOLOS = "$%&@#";

	public String generarPassword(String alfabeto, int size) {
		String password = "";

		for (int i = 0; i < size; i++) {
			password += alfabeto.charAt((int) (Math.random() * alfabeto.length()));
		}

		return password;
	}
}
