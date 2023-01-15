package com.hotel.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.hotel.factory.ConnectionFactory;

public class TestConnection {

	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();

        System.out.println("Cerrando la conexión");

        con.close();

	}

}
