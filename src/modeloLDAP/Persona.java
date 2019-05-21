package modeloLDAP;

import java.util.Arrays;

public abstract class Persona {
	
	protected String documento;
	protected String nombre;
	protected String apellidos;	
	protected String mail;	
	protected String uid;
	protected int gidNumber;
	protected int uidNumber;
	protected String fechaNacimiento;
	protected String password;
	protected byte[] foto;
	
	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}
	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the gidNumber
	 */
	public int getGidNumber() {
		return gidNumber;
	}
	/**
	 * @param gidNumber the gidNumber to set
	 */
	public void setGidNumber(int gidNumber) {
		this.gidNumber = gidNumber;
	}
	/**
	 * @return the uidNumber
	 */
	public int getUidNumber() {
		return uidNumber;
	}
	/**
	 * @param uidNumber the uidNumber to set
	 */
	public void setUidNumber(int uidNumber) {
		this.uidNumber = uidNumber;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the foto
	 */
	public byte[] getFoto() {
		return foto;
	}
	/**
	 * @param foto the foto to set
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [documento=" + documento + ", nombre=" + nombre + ", apellidos=" + apellidos + ", mail=" + mail
				+ ", uid=" + uid + ", gidNumber=" + gidNumber + ", uidNumber=" + uidNumber + ", fechaNacimiento="
				+ fechaNacimiento + ", password=" + password + ", foto=" + Arrays.toString(foto) + "]";
	}
	
	public abstract String obtenerIdentificacion();
	public abstract void establecerIdentificacion(String identificacion);
	
	
}
