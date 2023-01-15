package com.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;
	
	
	public ConnectionFactory() {

		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("root");
		pooledDataSource.setMaxPoolSize(10);
				
		this.dataSource = pooledDataSource;
	}
	
	
	public Connection recuperaConexion() {

		try {
			return this.dataSource.getConnection();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
