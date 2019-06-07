/**
 * 
 */
package modeloBBDD;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Objeto que almacena las variables de la reserva de la base de datos
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class Reserva {
	private LocalDate dia;
	private LocalTime hora;
	private String email;
	private int idPeriodo;

	/**
	 * @param dia
	 * @param hora
	 * @param email
	 * @param idPeriodo
	 */
	public Reserva(LocalDate dia, LocalTime hora, String email, int idPeriodo) {
		super();
		this.dia = dia;
		this.hora = hora;
		this.email = email;
		this.idPeriodo = idPeriodo;
	}

	/**
	 * @return the dia
	 */
	public LocalDate getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	/**
	 * @return the hora
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
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
	 * @return the idPeriodo
	 */
	public int getIdPeriodo() {
		return idPeriodo;
	}

	/**
	 * @param idPeriodo the idPeriodo to set
	 */
	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

}
