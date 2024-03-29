/**
 * 
 */
package modeloBBDD;

/**
 * Objeto Alumno para almacenar un Alumno de la base de datos
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class AlumnoBBDD {

	private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;

	/**
	 * @param email String que contiene el email del alumno
	 * @param nombre String del nombre del alumno
	 * @param apellido1 String del primer apellido
	 * @param apellido2 String del segundo apellido
	 * @param password string de las contraseña del alumno
	 */
	public AlumnoBBDD(String email, String nombre, String apellido1, String apellido2) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return the password
	 */

}
