package entity;

public class Flavor_recipe {
	private int recipeId;
	private int flavorId;
	private int percent;
	
	public Flavor_recipe(int recipeId, int flavorId, int percent) {
		this.recipeId = recipeId;
		this.flavorId = flavorId;
		this.percent = percent;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(int flavorId) {
		this.flavorId = flavorId;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
