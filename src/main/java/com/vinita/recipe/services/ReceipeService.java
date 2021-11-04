package com.vinita.recipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.vinita.recipe.models.Ingredient;
import com.vinita.recipe.models.IngredientQuantity;
import com.vinita.recipe.models.Recipe;
import com.vinita.recipe.models.User;
import com.vinita.recipe.repositories.IngredientQuantityRepository;
import com.vinita.recipe.repositories.IngredientRepository;
import com.vinita.recipe.repositories.ReceipeRepository;



@Service
public class ReceipeService {
	@Autowired
	private ReceipeRepository rRepo;
	@Autowired
	private IngredientRepository iRepo;
	@Autowired
	private IngredientQuantityRepository qRepo;
	
	
	//create
	public Recipe createReceipe(Recipe recipe) {
		
		List<IngredientQuantity> ingredientQuantity = recipe.getIngrediants();
		
		/*List<Ingredient> ingredients = new ArrayList<>();
		
		for (IngredientQuantity iq : ingredientQuantity) {
			Ingredient ingredient = iq.getIngredient();
			ingredient.setName(ingredient.getName().toLowerCase());
			if (!iRepo.existsByName(ingredient.getName())) {
				ingredients.add(ingredient);
			}
		}
		if (ingredients.size() > 0) {
			this.iRepo.saveAll(ingredients);
		}*/
	
		
		//recipe.setIngrediants(ingredientQuantity);

		Recipe savedRecipe = this.rRepo.save(recipe);
		
		// construct list of ingredient quantities
		for (IngredientQuantity iq : ingredientQuantity) {
			iq.setRecipe(savedRecipe);
			Ingredient ingredient = iRepo.findByName(iq.getIngredient().getName());
			if (ingredient != null) {
				iq.setIngredient(ingredient);
			}
		}
		qRepo.saveAll(ingredientQuantity);
		return savedRecipe;
		
		}
		
		
		
	//get one product
	public Recipe getReceipe(Long id) {
		return this.rRepo.findById(id).orElse(null);
	}
	//delete one product
	public void deleteReceipe(Long id) {
		this.rRepo.deleteById(id);
	}
	
	//update a product 
	public Recipe updateReceipe(Recipe recipe) {
		List<IngredientQuantity> ingredientQuantity = recipe.getIngrediants();
		Recipe updateRecipe = this.rRepo.save(recipe);
		
		
		//it is making new ingrediants again //solution may be to delete existing ingrediant first before coming here
		for (IngredientQuantity iq : ingredientQuantity) {
			iq.setRecipe(updateRecipe);
			Ingredient ingredient = iRepo.findByName(iq.getIngredient().getName());
			if (ingredient != null) {
				iq.setIngredient(ingredient);
			}
		}
		qRepo.saveAll(ingredientQuantity);
		return updateRecipe;
	}
		
		
		
	
	//get all product
	public List<Recipe> getAllReceipe(){
		return this.rRepo.findAll();
	}
	
	


	// adding likes to recipe from user
	public void userLikesRecipe(User user, Recipe recipe) {
		recipe.getUserLiked().add(user);
		this.rRepo.save(recipe);
	}
	
	
	//removing likes from recipe
	public void userDisLikesRecipe(User user, Recipe recipe) {
		recipe.getUserLiked().remove(user);
		this.rRepo.save(recipe);
	}
	
	
	// adding bookmark to recipe from user
	public void userSaveRecipe(User user, Recipe recipe) {
		recipe.getUserSaves().add(user);
		this.rRepo.save(recipe);
	}
	
	
	//removing bookmark from recipe
	public void userDisSaveRecipe(User user, Recipe recipe) {
		recipe.getUserSaves().remove(user);
		this.rRepo.save(recipe);
	}
	
	public List<Recipe> findAllByTitle(String title){
		return this.rRepo.findByTitleContaining(title);
	}
	public List<Recipe> topTwoLiked(){
		return this.rRepo.findTop4ByOrderByUserLikedDesc();
	}
	public List<Recipe> topTwoNew(){
		return this.rRepo.findTop4ByOrderByCreatedAtDesc();
	}
	
	public List<Recipe> findRecipeWithIngrediant(IngredientQuantity ing){
		return this.findRecipeWithIngrediant(ing);
	}
}
