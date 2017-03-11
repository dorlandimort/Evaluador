package mx.edu.ulsaoaxaca.evaluador.modelo.dao;

import mx.edu.ulsaoaxaca.evaluador.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.modelo.Pregunta;

public class AspiranteDAOImpl implements AspiranteDAO {
	
	private DataSource ds;
	
	public AspiranteDAOImpl() {
		this.ds = new DataSource();
	}

	@Override
	public Aspirante registrarAspirante(Aspirante aspirante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pregunta agregarPregunta(Aspirante aspirante, Pregunta pregunta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pregunta actualizarPregunta(Pregunta pregunta) {
		// TODO Auto-generated method stub
		return null;
	}

}
