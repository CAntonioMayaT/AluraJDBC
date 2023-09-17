package com.alura.jdbc.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.DAO.Categoria;
import com.alura.jdbc.DAO.ProductoDAO;
import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.Models.Producto;

public class ProductoController {

	private ProductoDAO productoDAO;

	public ProductoController() {
    	this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}	
	
	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		return productoDAO.modificar(nombre, descripcion, cantidad, id);
	}

	public int eliminar(Integer id){
		return productoDAO.eliminar(id);
	}

	public List<Producto> listar() {
		return productoDAO.listar();	
	}

	public List<Producto> listar(Categoria categoria) {
		return productoDAO.listar(categoria.getId());
	}

	
    public void guardar(Producto producto, Integer categoriaId) {
    	producto.setCategoriaId(categoriaId);
    	productoDAO.guardar(producto);
    }
	



}
