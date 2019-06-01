/**
 * 
 */
package modeloBBDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import configuracion.ConfiguracionSegura;

/**
 * 
 * Clase para gestionar las conexiones de la base de datos
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 * 
 */
public class Database {

	private String host;
	private String port;
	private String database;
	private String user;
	private String password;

	private Connection conexion;

	/**
	 * Constructor de Database
	 */
	public Database() {
		this.host = "127.0.0.1";
		this.port = "3306";
		this.database = "bbddProyecto";
		this.user = "amonfortp1";
		this.password = "1111";

	}

	/**
	 * 
	 * Metodo para conectarnos con la base de datos
	 * 
	 */
	public Connection conectar() {
		conexion = null;
		ConfiguracionSegura conf = new ConfiguracionSegura();

		try {
			conexion = DriverManager.getConnection(
					"jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + database + "?serverTimezone=UTC",
					conf.getUser(), conf.getPassword());
			if (conexion != null) {
				System.out.println("Conexion realizada con exito");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexion;
	}

	protected void desconectar() {
		try {
			conexion.close();
			System.out.println("Se a desconectado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Map<Integer, ArrayList<Object>> select(String columnas, String tabla, String where, String id) {
		Map<Integer, ArrayList<Object>> resultado = new LinkedHashMap<Integer, ArrayList<Object>>();
		ArrayList<Object> lista;

		String select = "SELECT " + columnas + " FROM " + tabla;
		if (where != null) {
			select += " WHERE " + where;
		}

		try (Connection con = conectar();
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(select)) {

			int numColumnas = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				lista = new ArrayList<Object>();
				for (int i = 1; i <= numColumnas; i++) {
					lista.add(rs.getObject(i));
				}
				resultado.put(rs.getInt(id), lista);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	protected int contarAlumno(String login, String passwd) {
		int i = 0;

		String select = "SELECT Count(*) FROM Alumno WHERE email=? and password=PASSWORD(?)";

		try (Connection con = conectar(); PreparedStatement pstm = con.prepareStatement(select);) {
			pstm.setString(1, login);
			pstm.setString(2, passwd);

			ResultSet rs = pstm.executeQuery();

			rs.next();

			i = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	protected boolean insert(String tabla, String columnas, String caracteres, ArrayList<String> datos) {
		boolean insertado = false;

		String insert = "INSERT INTO " + tabla + " (" + columnas + ") VALUES (" + caracteres + ") ";

		try (Connection con = conectar(); PreparedStatement pstm = con.prepareStatement(insert);) {
			System.out.println(insert);
			for (int i = 1; i <= datos.size(); i++) {
				pstm.setString(i, datos.get(i - 1));
			}
			pstm.execute();
			insertado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return insertado;
	}

	protected boolean callReservar(String email, LocalDate dia, LocalTime hora) {

		String sql = "{CALL reservar (?,?,?)}";

		try (Connection con = conectar(); CallableStatement cs = con.prepareCall(sql)) {
			cs.setString(1, email);
			cs.setDate(2, Date.valueOf(dia));
			cs.setTime(3, Time.valueOf(hora));

			cs.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
