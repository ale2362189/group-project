package entity;

import java.util.List;

public class Recipe {
	private int recipeId;
	private String name;
	private List<Flavor> flavors;
	
	public Recipe(int recipeId, String name, List<Flavor> flavors) {
		this.recipeId = recipeId;
		this.name = name;
		this.flavors = flavors;
		
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flavor> getFlavors() {
		return flavors;
	}

	public void setFlavors(List<Flavor> flavors) {
		this.flavors = flavors;
	}
}
