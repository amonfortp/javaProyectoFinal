/**
 * 
 */
package modeloBBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

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
	 * En este metodo preparamos los datos para enviarlos al metodo insert() y asi
	 * insertar al alumno
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

		String caracteres = "";

		for (int i = 0; i < datos.size(); i++) {
			if (i == datos.size() - 1) {
				caracteres += "PASSWORD(?)";
			} else {
				caracteres += "?,";
			}
		}

		return insert("Alumno", columnas, caracteres, datos);
	}

	public AlumnoBBDD obtenerAlumno(String email) {

		String sql = "SELECT nombre, apellido1, apellido2 FROM Alumno WHERE email= '" + email + "'";

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
			if (rs.next()) {
				return new AlumnoBBDD(email, rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * Metodo para obtener los dias de reseras y las horas
	 *
	 * @return Devuelve un Map cuya clave sera un LocalDate y el valor sera un
	 *         LinkedHashSet de LocalTime
	 */
	public Map<LocalDate, LinkedHashSet<LocalTime>> obtenerDiasHoras() {
		Map<LocalDate, LinkedHashSet<LocalTime>> citas = new LinkedHashMap<LocalDate, LinkedHashSet<LocalTime>>();
		LinkedHashSet<LocalTime> lista = new LinkedHashSet<LocalTime>();

		Date id = new Date(0);

		String sql = "SELECT dia, hora FROM Reserva WHERE dia>current_date() "
				+ "and email is null and idPeriodo in (SELECT idPeriodo FROM Periodo WHERE "
				+ "curso = YEAR(current_date()) and habilitado = TRUE) ORDER BY dia, hora ASC;";

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next()) {
				if (rs.getDate(1) != id) {
					id = rs.getDate(1);
				}

				lista.add(rs.getTime(2).toLocalTime());

				citas.remove(id.toLocalDate());
				citas.put(id.toLocalDate(), lista);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return citas;
	}

}
