package mx.edu.ulsaoaxaca.evaluador.mvc.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Sesion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date fecha;
	private String evaluador;
	
	public Sesion() {
		this.fecha = new Date();
	}
	
	private List<Aspirante> aspirantes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Aspirante> getAspirantes() {
		return aspirantes;
	}
	public void setAspirantes(List<Aspirante> aspirantes) {
		this.aspirantes = aspirantes;
	}
	public String getEvaluador() {
		return evaluador;
	}
	public void setEvaluador(String evaluador) {
		this.evaluador = evaluador;
	}
	
	
	
}
