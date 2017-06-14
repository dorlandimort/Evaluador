package mx.edu.ulsaoaxaca.evaluador.servicios.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
	
	private Connection connection;
	
	public DataSource() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			this.connection =
					DriverManager.getConnection("jdbc:mysql://localhost/evaluador?" +
				                                   "user=root");
			
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
	
	public Object ejecutarConsulta(PreparedStatement st) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int ejecutarActualizacion(PreparedStatement st) {
		int result = 0;
		try {
			result =  st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConn(Connection connection) {
		this.connection = connection;
	}
	
	
}
