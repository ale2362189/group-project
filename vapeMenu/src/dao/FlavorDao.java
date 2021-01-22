package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Flavor;

public class FlavorDao {
	private Connection connection;
	private final String GET_FLAVOR_BY_RECIPE_ID_QUERY = "SELECT * FROM flavors WHERE recipes_id = ?";
	private final String DELETE_FLAVOR_BY_RECIPE_ID_QUERY = "DELETE FROM flavors WHERE recipes_id = ?";
	private final String CREATE_NEW_FLAVOR_QUERY = "INSERT INTO flavors(name, id) values(?,?)";
	private final String UPDATE_FLAVOR_BY_NAME_QUERY = "UPDATE flavors SET name = ? WHERE name LIKE ?";
	private final String SEARCH_BY_ONE_FLAVOR_RETURN_RECIPE_LIST_QUERY = "  ";
	private final String ADD_FLAVOR_TO_DB_QUERY = " ";
	private final String GET_LAST_FLAVOR_ID = "SELECT id FROM flavors ORDER BY id desc limit 1";		
	
	public FlavorDao() {
		connection = DBConnection.getConnection();
	}

	public List<Flavor> getFlavorsByRecipeId(int Id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_FLAVOR_BY_RECIPE_ID_QUERY);
		ps.setInt(1, Id);
		ResultSet rs = ps.executeQuery();
		List<Flavor> flavors = new ArrayList<Flavor>();
		
		while (rs.next()) {
			flavors.add(new Flavor(rs.getInt(1), rs.getString(2)));
		}
		return flavors;
	}
	
	public void createNewFlavor(String name, int recipesId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_FLAVOR_QUERY);
		ps.setString(1, name);
		ps.setInt(2, recipesId);
		ps.executeUpdate();
	}
	
	public void deleteFlavorByRecipeId(int recipeId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_FLAVOR_BY_RECIPE_ID_QUERY);
		ps.setInt(1, recipeId);
		ps.executeUpdate();
	}
	public void updateFlavor(String newFlavor, String oldFlavor) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_FLAVOR_BY_NAME_QUERY);
		ps.setString(1, oldFlavor);
		ps.setString(2, newFlavor);
		ps.executeUpdate();
	}

	public void addFlavorToDB(String firstFlavor) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_FLAVOR_QUERY);
		ps.setString(1, firstFlavor);
		ps.executeQuery();
		 
		
	}
	public int getLastRow() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_LAST_FLAVOR_ID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
}
