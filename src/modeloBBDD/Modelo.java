/**
 * 
 */
package modeloBBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import java.util.TreeSet;

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
	 * Metodo para obtener los dias de reservas y las horas
	 *
	 * @return Devuelve un Map cuya clave sera un LocalDate y el valor sera un
	 *         TreeSet de LocalTime
	 */

	public Map<LocalDate, TreeSet<LocalTime>> obtenerDiasHoras() {
		Map<LocalDate, TreeSet<LocalTime>> citas = new LinkedHashMap<LocalDate, TreeSet<LocalTime>>();
		TreeSet<LocalTime> lista = new TreeSet<LocalTime>();

		Date dia = new Date(0);
		Date aux = new Date(0);

		String sql = "SELECT dia, hora FROM Reserva WHERE dia>current_date() and email is null and idPeriodo in (SELECT idPeriodo FROM Periodo WHERE curso = YEAR(current_date()) and habilitado = TRUE) ORDER BY dia, hora ASC;";
		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next()) {
				dia = rs.getDate(1);

				if (!aux.equals(dia)) {
					citas.put(aux.toLocalDate(), lista);
					lista = new TreeSet<LocalTime>();
					aux = dia;
				}
				lista.add(Time.valueOf(rs.getString(2)).toLocalTime());

			}
			citas.put(aux.toLocalDate(), lista);
			citas.remove(Date.valueOf("1970-01-01").toLocalDate());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return citas;
	}

	/**
	 * 
	 * Metodo para saber la reserva del alumno
	 *
	 * @param email email del Alumno
	 *
	 * @return Devuelve un string con el dia y la hora de la reserva
	 */
	public String obtenerReserva(String email) {
		String reserva = null;

		String sql = "SELECT dia, hora FROM Reserva WHERE email=?";

		try (Connection con = conectar(); PreparedStatement stm = con.prepareStatement(sql);) {
			stm.setString(1, email);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				reserva = rs.getString(1) + " " + rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reserva;

	}

	/**
	 * 
	 * En este metodo pasaremos las variables para poder reservar una cita
	 *
	 * @param email email del Alumno
	 * @param dia   LocalDate del dia que vamos a reservar
	 * @param hora  LocalTime de la hora de la que queremos la cita
	 *
	 * @return Devuelve boolean siendo un true si se a realizado la reserva
	 */
	public boolean reservar(String email, LocalDate dia, LocalTime hora) {

		return callReservar(email, dia, hora);

	}

	/**
	 * 
	 * Este metodo se utiliza para encontrar el mensaje que corresponde a cada tipo
	 * de correo que enviamos
	 *
	 * @param tipoMensaje identificador en la base de datos para la tabla mensajes
	 *
	 * @return Devuelve un string con el mensaje
	 */
	public String obtenerMensaje(String tipoMensaje) {

		String sql = "SELECT mensaje FROM Mensaje WHERE tipo LIKE '" + tipoMensaje+"'";

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * Metodo para eliminar la reserva del alumno
	 *
	 * @param email correo del alumno
	 *
	 * @return Devulve booleano siendo true si se a eliminado la reserva
	 */
	public boolean eliminarReserva(String email) throws SQLException {
		return callEliminarReserva(email);
	}

}
