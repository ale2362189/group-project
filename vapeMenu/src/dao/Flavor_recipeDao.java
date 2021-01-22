package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Flavor_recipeDao {
private final String CREATE_FULL_FLAVOR_RECIPE_QUERY = " ";
	
	private Connection connection;
	
	
	
	public Flavor_recipeDao() {
		connection = DBConnection.getConnection();
	}

	public void setFlavorRecipeAndPercent(int recipeId, int flavorId, int percent) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_FULL_FLAVOR_RECIPE_QUERY);
		ps.setInt(1, recipeId);
		ps.setInt(2, flavorId);
		ps.setInt(3, percent);
		ps.executeUpdate();
		
		
	}
}
