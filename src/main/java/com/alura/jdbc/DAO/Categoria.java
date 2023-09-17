package com.alura.jdbc.DAO;

public class Categoria {

	private Integer id; 
	
	private String nombre; 
	
	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Integer getId() {

		return this.id;
	}
	
	public String toString() {
		return this.nombre;
	}

	
}
