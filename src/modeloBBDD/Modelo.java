/**
 * 
 */
package modeloBBDD;

import java.util.ArrayList;

import com.lowagie.text.List;

/**
 * 
 * Clase que hereda de Database para ser intermediaria entre los metodos de
 * Database y los controladores
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class Modelo extends Database {

	public Modelo() {
		super();
	}

	/**
	 * 
	 * Metodo utilizado para comprobar que el login y el password son correctos en
	 * la base de datos
	 *
	 * @param login    login del usuario a validar
	 * @param password password del usuario a validar
	 * @return devuelve si hubo exito en autentificar o no
	 */
	public boolean autentificar(String login, String passwd) {
		boolean exito = false;

		if (contarAlumno(login, passwd) == 1) {
			exito = true;
		}

		return exito;
	}

	/**
	 * 
	 * (Descripcion del metodo)
	 *
	 * @param a      Objeto AlumnoBBDD que contendra todos los campos (sin contar la
	 *               contraseña) que necesitamos para insertar
	 * @param passwd contraseña del alumno
	 * @return Devolvera el resultado del insert
	 */
	public boolean insertarAlumno(AlumnoBBDD a, String passwd) {
		ArrayList<String> datos = new ArrayList<String>();

		datos.add(a.getEmail());
		datos.add(a.getNombre());
		datos.add(a.getApellido1());
		datos.add(a.getApellido2());
		datos.add(passwd);

		String columnas = "email, nombre, apellido1, apellido2, password";

		return insert("Alumno", columnas, datos);
	}

}
