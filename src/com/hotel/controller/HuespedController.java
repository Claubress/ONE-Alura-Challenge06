package com.hotel.controller;

import java.util.List;

import com.hotel.dao.HuespedDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.model.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;


	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	
	public List<Huesped> list() {
		return this.huespedDAO.list();
	}


	public List<Huesped> listId(String id) {
		return this.huespedDAO.listId(id);
	}

	
	public void save(Huesped huesped) {
		this.huespedDAO.save(huesped);
	}


	public void update(Huesped huesped) {
		this.huespedDAO.update(huesped);
	}


	public void delete(Integer id) {
		this.huespedDAO.delete(id);
		
	}

}
