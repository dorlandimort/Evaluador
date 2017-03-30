package mx.edu.ulsaoaxaca.evaluador.servicios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Aspirante;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Pregunta;
import mx.edu.ulsaoaxaca.evaluador.mvc.modelo.Sesion;

import java.util.Date;

public class AspiranteDAOImpl implements AspiranteDAO {
	
	private DataSource ds;
	private static AspiranteDAO dao;
	
	private AspiranteDAOImpl() {
		this.ds = new DataSource();
	}
	
	public static AspiranteDAO getInstance() {
		if (dao == null)
			dao = new AspiranteDAOImpl();
		return dao;
	}
	
	
	public Sesion registrarSesion(Sesion sesion) {
		String sql = "INSERT INTO sesion (fecha, evaluador) VALUES (?, ?)";
		
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setObject(1, sesion.getFecha(), java.sql.Types.DATE);
			st.setString(2, sesion.getEvaluador());
			int id = st.executeUpdate();
			if (id != -1)
				sesion.setId(id);
		} catch (SQLException e) {
			sesion = null;
			e.printStackTrace();
		}
		return sesion;
	}

	@Override
	public Aspirante registrarAspirante(Aspirante aspirante) {
		try {
			String sql = "INSERT INTO aspirante (nombre, edad, escolaridad, puesto) VALUES "
					+ "(?, ?, ?, ?)";
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, aspirante.getNombre());
			st.setInt(2,  aspirante.getEdad());
			st.setString(3, aspirante.getEscolaridad());
			st.setString(4, aspirante.getPuesto());
			
			int id = st.executeUpdate();
			if (id != -1)
				aspirante.setId(id);
			
		} catch (SQLException e) {
			aspirante = null;
			e.printStackTrace();
		}
		
		return aspirante;
	}

	private Aspirante obtenerAspirante(int id) {
		Aspirante aspirante = null;
		try {
			String sql = "SELECT id, nombre, edad. escolaridad, puesto FROM aspirante where id = ?";
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
		
			if (rs.next()) {
				aspirante = new Aspirante();
				aspirante.setId(rs.getInt("id"));
				aspirante.setNombre(rs.getString("nombre"));
				aspirante.setEdad(rs.getInt("edad"));
				aspirante.setEscolaridad(rs.getString("escolaridad"));
				aspirante.setPuesto(rs.getString("puesto"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aspirante;
	}
	
	private List<Pregunta> obtenerPreguntas(Aspirante aspirante) {
		List<Pregunta> preguntas = null;
		String sql = "SELECT id, pregunta, respuesta, correcta, fecha FROM pregunta "
				+ "WHERE aspirante_id = ?";
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql);
			st.setInt(1, aspirante.getId());
			ResultSet rs = st.executeQuery();
			
			preguntas = new LinkedList<>();
			while(rs.next()) {
				Pregunta p = new Pregunta();
				p.setId(rs.getInt("id"));
				p.setPregunta(rs.getString("pregunta"));
				p.setRespuesta(rs.getString("respuesta"));
				p.setFecha( (Date) rs.getObject("fecha"));
				p.setCorrecta(rs.getBoolean("correcta"));
				
				preguntas.add(p);
			}
		} catch (SQLException e) {
			preguntas = null;
			e.printStackTrace();
		}
		
		return preguntas;
	}
	
	@Override
	public Pregunta agregarPregunta(Aspirante aspirante, Pregunta pregunta) {
		
		return null;
	}

	@Override
	public Pregunta actualizarPregunta(Pregunta pregunta) {
		
		return null;
	}

}