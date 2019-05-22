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
		this.database = "bbddJava";
		this.user = "amonfortp1";
		this.password = "1111";

	}

	public Connection conectar() {

		conexion = null;
		ConfiguracionSegura conf = new ConfiguracionSegura();

		// System.out.println(conf.getUser());
		// System.out.println(conf.getPassword());

		try {
			conexion = DriverManager.getConnection(
					"jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + database + "?serverTimezone=UTC",
					conf.getUser(), conf.getPassword());
			if (conexion != null) {
				// System.out.println("Conexion realizada con exito");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexion;
	}

	protected void desconectar() {
		try {
			conexion.close();
			// System.out.println("Desconexion realizada con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
