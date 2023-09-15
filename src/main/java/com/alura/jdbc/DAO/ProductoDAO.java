package com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.Models.Producto;

public class ProductoDAO {

	final private Connection con;
	public List<Producto> listar() {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con){
			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");
			try(statement){
				statement.execute();
		
				final ResultSet resultSet = statement.getResultSet();
				
				List <Producto> resultado = new ArrayList<>();
				
				while(resultSet.next()) {
					Producto fila = new Producto(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("descripcion"), resultSet.getInt("cantidad"));
					
					resultado.add(fila);
					}
				return resultado;
			}	
		}	catch (SQLException e) {
			throw new RuntimeException(e);
			}
	
}
	
	public ProductoDAO (Connection con) {
		this.con = con;
		}
	
	public void guardar (Producto producto) {
		try(con){
			final PreparedStatement statement = con.prepareStatement("INSERT INTO producto(nombre, descripcion, cantidad) VALUES (?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			try (statement){
					ejecutaRegistro(producto, statement);
				} 
    	} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void ejecutaRegistro(Producto producto, PreparedStatement statement)
			throws SQLException {
	
		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());
		
		statement.execute();
		
		ResultSet resultSet = statement.getGeneratedKeys();
				
		while(resultSet.next()) {
			producto.setId(resultSet.getInt(1));
			System.out.println(String.format("Fue insertado el producto de %s", producto));
		}
	}
}
