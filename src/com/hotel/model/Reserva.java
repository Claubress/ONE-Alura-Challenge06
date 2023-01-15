package com.hotel.model;

import java.sql.Date;

public class Reserva {
	
	private Integer id; 
	private Date fechaEntrada; 
	private Date fechaSalida; 
	private Float valor; 
	private String formaPago;
	

	public Reserva(Date fechaEntrada, Date fechaSalida, float valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	
	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, float valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	

	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}


	public Date getFechaSalida() {
		return this.fechaSalida;
	}


	public Float getValor() {
		return this.valor;
	}


	public String getFormaPago() {
		return this.formaPago;
	}

}
