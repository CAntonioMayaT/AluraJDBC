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

import com.alura.jdbc.DAO.ProductoDAO;
import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.Models.Producto;

public class ProductoController {

	private ProductoDAO productoDAO;

	public ProductoController() {
    	this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}	
	
	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con){
			final PreparedStatement statement = con.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, cantidad = ? WHERE id = ?");
			try (statement){
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, cantidad);
						
				statement.execute();
				
				int udapteCount = statement.getUpdateCount();
				return udapteCount;
			}
		}
	}

	public int eliminar(Integer id) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
		
			}

		}
	}

	public List<Producto> listar() {
				return productoDAO.listar();
	}

    public void guardar(Producto producto) {
    	productoDAO.guardar(producto);
    }
	



}
