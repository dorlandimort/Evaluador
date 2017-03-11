package mx.edu.ulsaoaxaca.evaluador.modelo;

import java.util.Date;
import java.util.List;

public class Sesion {
	
	private int id;
	private Date fecha;
	
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
	
	
	
}
