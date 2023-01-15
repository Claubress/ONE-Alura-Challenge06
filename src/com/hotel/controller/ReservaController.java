package com.hotel.controller;

import java.util.List;

import com.hotel.dao.ReservaDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.model.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;


	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	

	public List<Reserva> list() {
		return this.reservaDAO.list();
	}

	
	public List<Reserva> listId(String id) {
		return this.reservaDAO.listId(id);
	}
	
	
	public void save(Reserva reserva) {
		reservaDAO.save(reserva);
	}


	public void update(Reserva reserva) {
		this.reservaDAO.update(reserva);		
	}


	public void delete(Integer id) {
		this.reservaDAO.delete(id);
	}
	
}
