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
import java.util.*;

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
	 * 
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
	public Map<LocalDate, TreeSet<LocalTime>> obtenerDiasHoras(boolean subSelect) {
		Map<LocalDate, TreeSet<LocalTime>> citas = new LinkedHashMap<LocalDate, TreeSet<LocalTime>>();
		TreeSet<LocalTime> lista = new TreeSet<LocalTime>();

		String select = "";

		if (subSelect == true) {
			select = "and idPeriodo in (SELECT idPeriodo FROM Periodo WHERE curso = YEAR(current_date()) and habilitado = TRUE)";
		}

		Date dia = new Date(0);
		Date aux = new Date(0);

		String sql = "SELECT dia, hora FROM Reserva WHERE dia>current_date() and email is null " + select
				+ " ORDER BY dia, hora ASC;";
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
	 * Metodo para obtener todos los cursos que hay en la base de datos
	 *
	 *
	 * @return Devuelve ArrayList<Integer> que contiene todos los cursos
	 */
	public ArrayList<Integer> obtenerCursos() {
		ArrayList<Integer> cursos = new ArrayList<Integer>();

		String sql = "SELECT curso FROM Curso";

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
			while (rs.next()) {
				cursos.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cursos;
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
	 * Metodo para obtener las reservas por periodo
	 *
	 * @param periodo Integer del identificador del periodo
	 *
	 * @return Devuelve un Map con todas las reservas de un determinado periodo
	 */
	public Map<Integer, Reserva> obtenerReservas(int periodo) {
		Map<Integer, Reserva> reservas = new LinkedHashMap<Integer, Reserva>();

		String sql = "SELECT dia, hora, email FROM Reserva WHERE idPeriodo = " + periodo;
		int i = 1;

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
			while (rs.next()) {
				Reserva r = new Reserva(rs.getDate(1).toLocalDate(), rs.getTime(2).toLocalTime(), rs.getString(3),
						periodo);
				reservas.put(i, r);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
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

		String sql = "SELECT mensaje FROM Mensaje WHERE tipo LIKE '" + tipoMensaje + "'";

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
	 * Metodo para obtener los Periodos segun su curso
	 *
	 * @param curso Variable int que se utilizara para identificar el curso
	 *
	 * @return Devolvera un Map con los periodos de cierto curso
	 */
	public Map<Integer, Periodo> obtenerPeriodoPorCurso(int curso) {
		Map<Integer, Periodo> periodos = new LinkedHashMap<Integer, Periodo>();

		String sql = "SELECT idPeriodo, diaInicio, diaFinal, horaInicio, horaFinal, tiempo, habilitado FROM Periodo WHERE curso = "
				+ curso;

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next()) {
				Periodo p = new Periodo(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(),
						rs.getTime(4).toLocalTime(), rs.getTime(5).toLocalTime(), rs.getTime(6).toLocalTime(), curso,
						rs.getBoolean(7));

				periodos.put(p.getId(), p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return periodos;
	}

	/**
	 * 
	 * Metodo para obtener los id de todos los periodos
	 *
	 * @return Devuelve TreeSet con los id de todos los periodos
	 */
	public TreeSet<Integer> obtenerPeriodos() {
		TreeSet<Integer> p = new TreeSet<Integer>();
		String sql = "SELECT idPeriodo FROM Periodo";

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next()) {
				p.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * 
	 * metodo para habilitar o deshabilitar un periodo
	 *
	 * @param id         Integer del id del periodo que vamos a actualizar
	 * @param habilitado boolean para deshabilitar el periodo o no
	 *
	 * @return Devolvera un boleano en caso de haber des/habilitado correctamente o
	 *         no
	 */
	public boolean cambiarHabilitado(int id, boolean habilitado) {
		boolean exito = false;

		String sql = "UPDATE Periodo SET habilitado = " + habilitado + " WHERE idPeriodo = " + id;

		try (Connection con = conectar(); Statement stm = con.createStatement();) {
			if (stm.execute(sql)) {
				exito = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exito;
	}

	/**
	 * 
	 * Metodo para obtener todos los mensajes
	 *
	 * 
	 * @return Devuelve los mensajes dentro de un HashSet
	 */
	public HashSet<Mensaje> obtenerMensajes() {
		HashSet<Mensaje> mensajes = new HashSet<Mensaje>();

		String sql = "SELECT tipo, mensaje FROM Mensaje";

		try (Connection con = conectar(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next()) {
				Mensaje m = new Mensaje(rs.getString(1), rs.getString(2));

				mensajes.add(m);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensajes;
	}

	/**
	 * 
	 * Metodo para cambiar el mensaje
	 *
	 * @param tipo    String para identificar le mensaje que se va a cambiar
	 * @param mensaje String con el nuevo mensaje
	 *
	 * @return Devuelve un booleano siendo tru si se a cambiado el mensaje
	 */
	public boolean cambiarMensaje(String tipo, String mensaje) {
		boolean exito = false;

		String sql = "UPDATE Mensaje SET mensaje = '" + mensaje + "' WHERE tipo = '" + tipo + "'";

		try (Connection con = conectar(); Statement stm = con.createStatement();) {

			stm.execute(sql);
			exito = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exito;
	}

	/**
	 * 
	 * Metodo para eliminar la reserva del alumno
	 *
	 * @param email correo del alumno
	 *
	 * @return Devuelve booleano siendo true si se a eliminado la reserva
	 */
	public boolean eliminarReserva(String email) throws SQLException {
		return callEliminarReserva(email);
	}

	/**
	 * 
	 * Metodo que llama al metodo crearPeriodo
	 *
	 * @param periodo Objeto periodo que contiene los datos necesarios para utilizar
	 *                en el metodo callCrearPeriodo para crear los periodos
	 *
	 * @return Devuelve boolean siendo true si se a creado el periodo
	 */
	public boolean crearPeriodo(Periodo periodo) {
		return callCrearPeriodo(periodo);
	}

}
