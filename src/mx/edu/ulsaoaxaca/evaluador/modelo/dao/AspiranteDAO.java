package mx.edu.ulsaoaxaca.evaluador.modelo.dao;

import mx.edu.ulsaoaxaca.evaluador.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.modelo.Pregunta;

public interface AspiranteDAO {

	public Aspirante registrarAspirante(Aspirante aspirante);
	public Pregunta agregarPregunta(Aspirante aspirante, Pregunta pregunta);
	public Pregunta actualizarPregunta (Pregunta pregunta);
	
}
