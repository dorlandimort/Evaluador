package mx.edu.ulsaoaxaca.evaluador.modelo;

import java.util.Date;

public class Pregunta {
	
	private int id;
	private String pregunta;
	private String respuesta;
	private boolean esCorrecta;
	private Date fecha;
	
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
	public boolean isEsCorrecta() {
		return esCorrecta;
	}
	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
