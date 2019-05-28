/**
 * 
 */
package modeloBBDD;

/**
 * 
 * Explicacion de la clase
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

	public String generarPassword(String alfabeto, int tamaño) {
		String contraseña = "";

		for (int i = 1; i < tamaño; i++) {
			contraseña += alfabeto.charAt((int) (Math.random() * alfabeto.length()));
		}

		return contraseña;
	}
}
