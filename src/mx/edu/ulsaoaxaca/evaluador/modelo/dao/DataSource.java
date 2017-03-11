package mx.edu.ulsaoaxaca.evaluador.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
	
	private Connection conn;
	
	public DataSource() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn =
					DriverManager.getConnection("jdbc:mysql://localhost/evaluador?" +
				                                   "user=root&password=toor");
			
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object ejecutarConsulta(String consulta) {
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(consulta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int ejecutarActualizacion(String actualizacion) {
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result =  st.executeUpdate(actualizacion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
