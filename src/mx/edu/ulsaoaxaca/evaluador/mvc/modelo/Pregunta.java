package mx.edu.ulsaoaxaca.evaluador.mvc.modelo;

import java.io.Serializable;
import java.util.Date;

public class Pregunta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3859746295320702919L;
	private int id;
	private String pregunta;
	private String respuesta;
	private boolean correcta;
	private Date fecha;
	
	public Pregunta() {
		this("");
	}
	
	public Pregunta(String pregunta) {
		this.fecha = new Date();
		this.correcta = false;
		this.pregunta = pregunta;
	}
	
	public String toString() {
		return this.pregunta;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public boolean isCorrecta() {
		return correcta;
	}
	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

}
