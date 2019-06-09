/**
 * 
 */
package modeloBBDD;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Objeto que almacena las variables del periodo de la base de datos
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class Periodo {

	private int id;
	private LocalDate diaInicio;
	private LocalDate diaFinal;
	private LocalTime horaInicio;
	private LocalTime horaFinal;
	private LocalTime tiempo;
	private int curso;
	private boolean habilitado;

	/**
	 * @param id Integer del id del periodo
	 * @param diaInicio DATE del dia del inicio del periodo
	 * @param diaFinal DATE del dia final del periodo
	 * @param horaInicio TIME de la hora de inicio
	 * @param horaFinal TIME de la hora final
	 * @param tiempo TIME del tiempo que dura cada reserva
	 * @param curso Integer que representa el curso
	 * @param habilitado BOOLEAN siendo true si esta habilitado
	 */
	public Periodo(int id, LocalDate diaInicio, LocalDate diaFinal, LocalTime horaInicio, LocalTime horaFinal,
			LocalTime tiempo, int curso, boolean habilitado) {
		super();
		this.id = id;
		this.diaInicio = diaInicio;
		this.diaFinal = diaFinal;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.tiempo = tiempo;
		this.curso = curso;
		this.habilitado = habilitado;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the diaInicio
	 */
	public LocalDate getDiaInicio() {
		return diaInicio;
	}

	/**
	 * @param diaInicio the diaInicio to set
	 */
	public void setDiaInicio(LocalDate diaInicio) {
		this.diaInicio = diaInicio;
	}

	/**
	 * @return the diaFinal
	 */
	public LocalDate getDiaFinal() {
		return diaFinal;
	}

	/**
	 * @param diaFinal the diaFinal to set
	 */
	public void setDiaFinal(LocalDate diaFinal) {
		this.diaFinal = diaFinal;
	}

	/**
	 * @return the horaInicio
	 */
	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFinal
	 */
	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	/**
	 * @param horaFinal the horaFinal to set
	 */
	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	/**
	 * @return the tiempo
	 */
	public LocalTime getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(LocalTime tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return the curso
	 */
	public int getCurso() {
		return curso;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(int curso) {
		this.curso = curso;
	}

	/**
	 * @return the habilitado
	 */
	public boolean isHabilitado() {
		return habilitado;
	}

	/**
	 * @param habilitado the habilitado to set
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}
