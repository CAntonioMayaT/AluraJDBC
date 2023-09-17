package com.alura.jdbc.Controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.DAO.Categoria;
import com.alura.jdbc.DAO.CategoriaDAO;
import com.alura.jdbc.Factory.ConnectionFactory;

public class CategoriaController {

	private CategoriaDAO categoriaDAO;
	
	public CategoriaController() {
		var factory = new ConnectionFactory();
		this.categoriaDAO = new CategoriaDAO(factory.recuperaConexion());
	}
	
	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}

    public List<Categoria> cargaReporte() {
        return CategoriaDAO.listarConProductos();
    }

}
