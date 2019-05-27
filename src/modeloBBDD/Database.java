/**
 * 
 */
package modeloBBDD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import configuracion.ConfiguracionSegura;

public class Database {

	private String host;
	private String port;
	private String database;
	private String user;
	private String password;

	private Connection conexion;

	public Database() {

		this.host = "127.0.0.1";
		this.port = "3306";
		this.database = "bbddProyecto";
		this.user = "amonfortp1";
		this.password = "1111";

	}

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

	protected Map<Integer, ArrayList<Object>> select(String columnas, String tabla, String where) {
		Map<Integer, ArrayList<Object>> resultado = new LinkedHashMap<Integer, ArrayList<Object>>();
		
		
		
		return resultado;
	}

}
