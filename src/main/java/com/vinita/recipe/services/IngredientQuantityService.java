package com.vinita.recipe.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.recipe.models.IngredientQuantity;
import com.vinita.recipe.repositories.IngredientQuantityRepository;



@Service
public class IngredientQuantityService {
	@Autowired
	private IngredientQuantityRepository qRepo;
	
	
	//create
	public IngredientQuantity createIngredientQuantity(IngredientQuantity ingredientQuantity) {
		return this.qRepo.save(ingredientQuantity);
	}
	//get one ingredientQuantity
	public IngredientQuantity getIngredientQuantity(Long id) {
		return this.qRepo.findById(id).orElse(null);
	}
	//delete one ingredientQuantity
	public void deleteIngredientQuantity(Long id) {
		this.qRepo.deleteById(id);
	}
	
	// delete all entries for a ingredientQuantity
	public void deleteIngedientQuantitiesByRecipe(Long id) {
		this.qRepo.deleteByRecipeId(id);
	}
	
	//update a ingredientQuantity 
	public IngredientQuantity updateReceipe(IngredientQuantity ingredientQuantity) {
		return this.qRepo.save(ingredientQuantity);
	}
	

}
