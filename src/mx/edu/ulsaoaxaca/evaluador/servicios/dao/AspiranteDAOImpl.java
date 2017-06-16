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
			st.executeUpdate();
			int id = -1;
			ResultSet rs = st.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
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
			String sql = "INSERT INTO aspirante (nombre, edad, escolaridad, puesto, sesion_id) VALUES "
					+ "(?, ?, ?, ?, ?);";
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, aspirante.getNombre());
			st.setInt(2,  aspirante.getEdad());
			st.setString(3, aspirante.getEscolaridad());
			st.setString(4, aspirante.getPuesto());
			st.setInt(5, aspirante.getSesion().getId());
			
			st.executeUpdate();
			
			int id = -1;
			ResultSet rs = st.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
				System.out.println("Id:" + id);
			}
			if (id != -1)
				aspirante.setId(id);
			
		} catch (SQLException e) {
			System.out.println("Error de base de datos");
			aspirante = null;
			e.printStackTrace();
		}
		
		return aspirante;
	}

	@SuppressWarnings("unused")
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
	
	public List<Pregunta> obtenerPreguntas(Aspirante aspirante) {
		List<Pregunta> preguntas = new LinkedList<>();
		String sql = "SELECT id, pregunta, respuesta, correcta, fecha FROM pregunta "
				+ "WHERE aspirante_id = ?";
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql);
			st.setInt(1, aspirante.getId());
			ResultSet rs = st.executeQuery();
			
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
		String sql = "INSERT INTO pregunta (pregunta, fecha, Aspirante_id) values (?, ?, ?);";
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setObject(1, pregunta.getPregunta());
			st.setObject(2, pregunta.getFecha(), java.sql.Types.DATE);
			st.setInt(3,  aspirante.getId());
			st.executeUpdate();
			int id = -1;
			ResultSet rs = st.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			if (id != -1) {
				pregunta.setId(id);
				aspirante.getPreguntas().add(pregunta);
			}
		} catch (SQLException e) {
			pregunta = null;
			e.printStackTrace();
		}
		return pregunta;
	}

	@Override
	public Pregunta actualizarPregunta(Pregunta pregunta) {
		String sql = "UPDATE pregunta set respuesta = ? where id = ?;";
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, pregunta.getRespuesta());
			st.setInt(2, pregunta.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			pregunta = null;
			e.printStackTrace();
		}
		return pregunta;
	}

	@Override
	public void calificarPregunta(int id, boolean correcta) {
		String sql = "UPDATE pregunta set correcta = ? where id = ?;";
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setBoolean(1, correcta);
			st.setInt(2, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int contarPreguntas(Aspirante aspirante) {
		String sql = "select count(*) from pregunta where Aspirante_id = ?;";
		int n = -1;
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql);
			st.setInt(1, aspirante.getId());
			ResultSet rs = st.executeQuery();
			
			if (rs != null && rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int contarPreguntasCorrectas(Aspirante aspirante) {
		String sql = "select count(*) from pregunta where Aspirante_id = ? and correcta = true;";
		int n = -1;
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql);
			st.setInt(1, aspirante.getId());
			ResultSet rs = st.executeQuery();
			
			if (rs != null && rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Aspirante puntuarAspirante(Aspirante aspirante) {
		String sql = "UPDATE aspirante set puntuacion = ? where id = ?;";
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setDouble(1, aspirante.getPuntuacion());
			st.setInt(2, aspirante.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			aspirante= null;
			e.printStackTrace();
		}
		return aspirante;
	}

	@Override
	public int obtenerPuntuacion(Aspirante aspirante) {
		String sql = "select puntuacion from aspirante where id = ?;";
		int n = -1;
		try {
			PreparedStatement st = this.ds.getConnection().prepareStatement(sql);
			st.setInt(1, aspirante.getId());
			ResultSet rs = st.executeQuery();
			
			if (rs != null && rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

}
