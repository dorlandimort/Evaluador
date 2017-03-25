package mx.edu.ulsaoaxaca.evaluador.servicios.dao;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Sesion;

public interface AspiranteDAO {

	public Sesion registrarSesion(Sesion sesion);
	public Aspirante registrarAspirante(Aspirante aspirante);
	public Pregunta agregarPregunta(Aspirante aspirante, Pregunta pregunta);
	public Pregunta actualizarPregunta (Pregunta pregunta);
	
}
