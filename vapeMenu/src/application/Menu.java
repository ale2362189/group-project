package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.FlavorDao;
import dao.Flavor_recipeDao;
import dao.RecipeDao;
import entity.Flavor;
import entity.Recipe;

public class Menu {
	private Flavor_recipeDao flavor_recipeDao = new Flavor_recipeDao();
	private FlavorDao flavorDao = new FlavorDao();
	private RecipeDao recipeDao = new RecipeDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> menuChoices = Arrays.asList(
			"See a list of available recipes", 
			"See a single recipe with its flavors",
			"Create a new recipe",
			"Delete an old recipe",
			"Replace an old flavor with a new one",
			"Search for a recipe by flavor"
			);
	
	
public void start() {
	String selection = "";
	
	do {
		printMenu();
		selection = scanner.nextLine();
		
		try {
	    	if (selection.equals("1")) {
			    displayAllRecipes();
		    } else if (selection.equals("2")) {
			    displaySingleRecipe();
		    } else if (selection.equals("3")) {
			    CreateRecipe();
		    } else if (selection.equals("4")) {
			    deleteRecipe();
		    } else if (selection.equals("5")) {
			    replaceOldFlavor();
		    }// else if (selection.equals("6")) {
		    	//searchByFlavor();
		   // }
	    	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Press enter to continue.....");
		scanner.nextLine();
	} while(!selection.equals("-1"));
}


private void printMenu() {
	System.out.println("Select an option\n----------------------------------");
	for (int i = 0; i < menuChoices.size(); i++) {
		System.out.println(i + 1 + ") " + menuChoices.get(i));
		}
		System.out.println("\t OR enter -1 to exit");
}

private void displayAllRecipes() throws SQLException {
	List<Recipe> recipes = recipeDao.getRecipes();
	for (Recipe recipe : recipes) {
		System.out.println(recipe.getRecipeId() + ": " + recipe.getName());
		
	}
}
private void displaySingleRecipe() throws SQLException {
	System.out.print("Enter recipe id: ");
	int id = Integer.parseInt(scanner.nextLine());
	Recipe recipe = recipeDao.getRecipeById(id);
	System.out.println(recipe.getRecipeId() + ": " + recipe.getName());
	for (Flavor flavor : recipe.getFlavors()) {
		System.out.println("\tFlavor " + flavor.getFlavorId() + " - Name: " + flavor.getFlavor());
	}
}
private void CreateRecipe() throws SQLException {
	System.out.print("Enter new recipe name: ");
	String recipeName = scanner.nextLine();
	recipeDao.createRecipe(recipeName);
	addNewFlavor();
	
}
private void deleteRecipe() throws SQLException {
	System.out.print("Enter recipe id to delete: ");
	int id = Integer.parseInt(scanner.nextLine());
	recipeDao.deleteRecipeById(id);
}
private void addNewFlavor() throws SQLException {
	System.out.println("you may add up to 3 flavors");
	System.out.print("how many flavors are you adding?");
	int amountChoice = scanner.nextInt();
	
		if (amountChoice == 2) {
			System.out.println("Enter first flavor to add:");
			String firstFlavor = scanner.nextLine();
			System.out.println("Press enter to continue.....");
			scanner.nextLine();
			System.out.println("what percentage of the recipe is that flavor? ");
			int percent = Integer.parseInt(scanner.nextLine());
		    flavorDao.addFlavorToDB(firstFlavor);
		    int flavorId = flavorDao.getLastRow();
		    int recipeId = recipeDao.getLastRow();
		    flavor_recipeDao.setFlavorRecipeAndPercent(recipeId, flavorId, percent);
		    
		
			
			System.out.print("Enter second flavor to add:");
			String secondFlavor = scanner.nextLine();
			System.out.print("what percentage of the recipe is that flavor? ");
			int secondPercentOption = Integer.parseInt(scanner.nextLine());
			
			
		} else if (amountChoice == 3) {
			System.out.print("Enter first flavor to add:");
			String firstFlavor = scanner.nextLine();
			System.out.print("what percentage of the recipe is that flavor? ");
			int firstPercentOption = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter second flavor to add:");
			String secondFlavor = scanner.nextLine();
			System.out.print("what percentage of the recipe is that flavor? ");
			int secondPercentOption = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter third flavor to add:");
			String thirdFlavor = scanner.nextLine();
			System.out.print("what percentage of the recipe is that flavor? ");
			int thirdPercentOption = Integer.parseInt(scanner.nextLine());
		//	flavorDao.addFlavorToDB();
		}
		
}
		//  
	   

private void replaceOldFlavor() throws SQLException {
	System.out.print("This will permenently remove a flavor, "
			+ "\nand replace it with another flavor in every recipe."
			+ "\nWhat is the flavor you would like to replace?");
	String oldFlavor = scanner.nextLine();
	System.out.print("what is the flavor you are replacing it with?");
	String newFlavor = scanner.nextLine();
	flavorDao.updateFlavor(oldFlavor, newFlavor);
	

  }
//private void searchByFlavor() {
	//System.out.println("enter the flavor that you want to search:");
	//String selection = scanner.next();
	//flavorDao.searchByOneflavor(selection);
	
//}

}
