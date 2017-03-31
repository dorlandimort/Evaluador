package mx.edu.ulsaoaxaca.evaluador.mvc.modelo;

import java.io.Serializable;
import java.util.Date;

public class Pregunta implements Serializable {
	
	private int id;
	private String pregunta;
	private String respuesta;
	private boolean correcta;
	private Date fecha;
	
	public Pregunta() {
		this.fecha = new Date();
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
