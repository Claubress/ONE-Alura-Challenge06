package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Reserva;

public class ReservaDAO {
	
	private Connection con;


	public ReservaDAO(Connection con) {
		this.con = con;
	}
	

	public void save(Reserva reserva) {
		
		try {
			
			final PreparedStatement statement = con.prepareStatement("INSERT INTO reserva(fecha_entrada, fecha_salida, valor, forma_pago) "
					+ "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			try(statement) {

				statement.setDate(1, (java.sql.Date) reserva.getFechaEntrada());
				statement.setDate(2, (java.sql.Date) reserva.getFechaSalida());
				statement.setFloat(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
		    	
		    	statement.execute();
		    	
		    	final ResultSet resultSet = statement.getGeneratedKeys();

		    	try(resultSet) {
			    	while (resultSet.next()) {
			    		
			    		reserva.setId(resultSet.getInt(1));
			    					
					}
		    	}    	
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


    public List<Reserva> list() {

    	List<Reserva> reservas = new ArrayList<Reserva>();

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reserva");
			
			try(statement) {
		    	statement.execute();
				ResultSetToReserva(reservas, statement);
				
			}
			return reservas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

    
    private void ResultSetToReserva(List<Reserva> reservas, PreparedStatement statement) throws SQLException {

    	try (ResultSet rst = statement.getResultSet()) {
			while (rst.next()) {
				Reserva produto = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getFloat(4), rst.getString(5));

				reservas.add(produto);
			}
		}
	}



	public List<Reserva> listId(String id) {

		List<Reserva> reservas = new ArrayList<Reserva>();

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reserva "
					+ "WHERE id = ?");
			
			try(statement) {

				statement.setString(1, id);

				statement.execute();
				ResultSetToReserva(reservas, statement);
				
			}
			return reservas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void update(Reserva reserva) {

		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE reserva SET fecha_entrada = ?, fecha_salida = ?, "
					+ "valor = ?, forma_pago = ? WHERE id = ?");
			try(statement) {
				statement.setDate(1, (java.sql.Date) reserva.getFechaEntrada());
				statement.setDate(2, (java.sql.Date) reserva.getFechaSalida());
				statement.setFloat(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());
		    	statement.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


	public void delete(Integer id) {

		try {

			final PreparedStatement statement = con.prepareStatement("DELETE FROM reserva WHERE id = ?");
			
			try(statement) {
		    	statement.setInt(1, id);
		    	statement.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}	

}
