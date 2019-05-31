/**
 * 
 */
package main;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import configuracion.ConfiguracionSegura;
import gmail.GmailTool;
import modeloBBDD.Modelo;
import modeloBBDD.Password;
import modeloLDAP.LDAP;
import modeloLDAP.Persona;
import modeloLDAP.Profesor;

/**
 * Explicacion de la clase
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// LDAP

//		ArrayList<Persona> listado = new ArrayList<Persona>();
//
//		LDAP ldap = new LDAP();
//
//		// Listar miembros de un grupo
//		listado = ldap.memberOf("Docente");
//		System.out.println(listado);
//
//		Profesor docente = new Profesor();
//		docente.setUid("jfajardo");
//		ldap.obtenerUsuarioLDAPByUID(docente);
//
//		// Autentificamos el usuario
//		System.out.println("Es correcto el password:" + ldap.autenticacionLDAP("jfajardo", "1111"));
//
//		// Pertenece a un grupo
//		System.out.println("Pertenece al grupo Docente: "
//				+ ldap.search("cn=Docente,ou=groups", "memberUid=" + docente.getUidNumber()));

		/*
		 * //GMAIL
		 * 
		 * // Cambiar el destinatario
		 * 
		 * ConfiguracionSegura conf = new ConfiguracionSegura();
		 * 
		 * System.out.println(conf.getMailAccessToken());
		 * System.out.println(conf.getMailClientId());
		 * System.out.println(conf.getMailClientSecret());
		 * System.out.println(conf.getMailFrom());
		 * System.out.println(conf.getMailRefreshToken());
		 * System.out.println(conf.getMailSmtpReply());
		 * 
		 * String to = "amonfortparra@gmail.com";
		 * 
		 * String from = (new ConfiguracionSegura()).getMailFrom(); String subject =
		 * "Mail de prueba"; String body = "Esto es una prueba";
		 * 
		 * ArrayList<File> files = new ArrayList<File>(); files.add(new
		 * File("DDL.sql"));
		 * 
		 * // Envios de mensajes de prueba GmailTool.sendHtml(to, from, subject, body);
		 * GmailTool.sendHtmlWithAttachment(to, from, subject, body, files);
		 * 
		 * 
		 */

//		Password c = new Password();
//
//		System.out.println(c.generarPassword(c.MAYUSCULAS + c.NUMEROS + c.SIMBOLOS, 8));

		Modelo modelo = new Modelo();

		Map<LocalDate, LinkedHashSet<LocalTime>> periodos = modelo.obtenerDiasHoras();

		for (LocalDate key : periodos.keySet()) {
			System.out.println(key);
			System.out.println(periodos.get(key));
		}

	}
}
