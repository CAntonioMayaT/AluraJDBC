package com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
	
	private static Connection con; 

	public CategoriaDAO(Connection recuperaConexion) {
		this.con = recuperaConexion;
	}

	public List<Categoria> listar() {
		List<Categoria> resultado = new ArrayList<Categoria>();
		
		try {
				
			final PreparedStatement statement = con.prepareStatement(
				"SELECT ID, NOMBRE FROM CATEGORIA");
		
			
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var categoria = new Categoria(resultSet.getInt("id"), resultSet.getString("nombre"));
						
						resultado.add(categoria);
						
					}
					}
				}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}


	public static List<Categoria> listarConProductos() throws SQLException {
		List<Categoria> resultado = new ArrayList<Categoria>();
		
		try {
				
			String querySelect = "SELECT C.id, C.nombre, P.id, P.nombre, P.cantidad "
			+ "FROM categoria C "
			+ "INNER JOIN Producto P ON C.id = P.Categoria_id";
			final PreparedStatement statement = con.prepareStatement(
				querySelect);
			System.out.println(querySelect);
			
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						int categoriaId = resultSet.getInt("id");
						String categoriaNombre = resultSet.getString("nombre");
						Categoria categoria = resultado
								.stream()
								.filter(cat -> cat.getId().equals(categoriaId))
								.findAny().orElseGet(() -> { 
									Categoria cat = new Categoria(categoriaId, categoriaNombre);
								
						
						resultado.add(categoria);
						return cat;
								});
					  }
					}
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

		
	} finally {};
	}
}
