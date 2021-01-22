package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Recipe;

public class RecipeDao {

	private Connection connection;
	private FlavorDao flavorDao;
	private final String GET_RECIPES_QUERY = "SELECT * FROM recipes";
	private final String GET_SINGLE_RECIPE_BY_ID_QUERY = "SELECT * FROM recipes WHERE id = ?";
	private final String CREATE_NEW_FLAVOR_QUERY = "INSERT INTO recipes(name) VALUES(?)";
	private final String DELETE_RECIPE_BY_ID_QUERY = "DELETE FROM recipes WHERE id = ?";
	private final String GET_LAST_RECIPES_ID = "SELECT id FROM recipes ORDER BY id desc limit 1";
	
	public RecipeDao() {
		connection = DBConnection.getConnection();
		flavorDao = new FlavorDao();
	}
	
	public List<Recipe> getRecipes() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_RECIPES_QUERY).executeQuery();
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		while (rs.next()) {
			recipes.add(populateRecipe(rs.getInt(1), rs.getString(2)));
		}
		
		return recipes;
	}
	
	public Recipe getRecipeById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_SINGLE_RECIPE_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateRecipe(rs.getInt(1), rs.getString(2));
		
	}
	
	public void createRecipe(String recipeName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_FLAVOR_QUERY);
		ps.setString(1, recipeName);
		ps.executeUpdate();
		
	}
	
	public void deleteRecipeById(int id) throws SQLException { 
		flavorDao.deleteFlavorByRecipeId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_RECIPE_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	public int getLastRow() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_LAST_RECIPES_ID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	private Recipe populateRecipe(int id, String name) throws SQLException {
		return new Recipe(id, name, flavorDao.getFlavorsByRecipeId(id));
	}
}
