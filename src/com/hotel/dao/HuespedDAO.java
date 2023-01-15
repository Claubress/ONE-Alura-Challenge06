package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Huesped;

public class HuespedDAO {
	
	private Connection con;
	

	public HuespedDAO(Connection con) {
		this.con = con;
	}


	public List<Huesped> list() {

		List<Huesped> huespedes = new ArrayList<Huesped>();

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, "
					+ "nacionalidad, telefono, reserva_id FROM huesped");
			
			try(statement) {
		    	statement.execute();
			
		    	ResultSetToHuesped(huespedes, statement);
				
			}
			return huespedes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}	


	public List<Huesped> listId(String id) {

		List<Huesped> huespedes = new ArrayList<Huesped>();

		try {


			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, "
					+ "nacionalidad, telefono, reserva_id FROM huesped WHERE id = ?");
			
			try(statement) {

				statement.setString(1, id);

				statement.execute();
				ResultSetToHuesped(huespedes, statement);
				
			}
			return huespedes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	private void ResultSetToHuesped(List<Huesped> huespedes, PreparedStatement statement) throws SQLException {

		try (ResultSet resultSet = statement.getResultSet()) {
			while (resultSet.next()) {
				Huesped huesped = new Huesped(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));

				huespedes.add(huesped);

			}
		}
	}



	public void save(Huesped huesped) {

		try {
			
			final PreparedStatement statement = con.prepareStatement("INSERT INTO huesped(nombre, apellido, fecha_nacimiento, nacionalidad,"
					+ "telefono, reserva_id) "
					+ "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			try(statement) {

				statement.setString(1, huesped.getNombre());
		    	statement.setString(2, huesped.getApellido());
		    	statement.setDate(3, (java.sql.Date) huesped.getFechaNacimiento());
		    	statement.setString(4, huesped.getNacionalidad());
		    	statement.setString(5, huesped.getTelefono());
		    	statement.setInt(6, huesped.getIdReserva());
		    	    	
		    	statement.execute();
		    	
		    	final ResultSet resultSet = statement.getGeneratedKeys();

		    	try(resultSet) {
			    	while (resultSet.next()) {
			    		huesped.setId(resultSet.getInt(1));			
					}
		    	}    	

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}	

	
	public void update(Huesped huesped) {

		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE huesped SET nombre = ?, apellido = ?, "
					+ "fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, reserva_id = ? WHERE id = ?");
			
			try(statement) {

				statement.setString(1, huesped.getNombre());
		    	statement.setString(2, huesped.getApellido());
		    	statement.setDate(3, (java.sql.Date) huesped.getFechaNacimiento());
		    	statement.setString(4, huesped.getNacionalidad());
		    	statement.setString(5, huesped.getTelefono());
		    	statement.setInt(6, huesped.getIdReserva());
		    	statement.setInt(7, huesped.getId());
		    	    	
		    	statement.execute();
		    	
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


	public void delete(Integer id) {

		try {

			final PreparedStatement statement = con.prepareStatement("DELETE FROM huesped WHERE id = ?");
			
			try(statement) {
		    	statement.setInt(1, id);
		    	statement.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}	

}

