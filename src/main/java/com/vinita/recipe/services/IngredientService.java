package com.vinita.recipe.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.recipe.models.Ingredient;
import com.vinita.recipe.repositories.IngredientRepository;



@Service
public class IngredientService {
	@Autowired
	private IngredientRepository iRepo;
	
	
	//create ingredient
	public Ingredient createIngredient(Ingredient ingredient) {
		if (!this.iRepo.existsByName(ingredient.getName())) {
			this.iRepo.save(ingredient);
		}
		return this.iRepo.findByName(ingredient.getName());
	}
	//get one ingredient
	public Ingredient getIngredient(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	//delete one ingredient
	public void deleteIngredient(Long id) {
		this.iRepo.deleteById(id);
	}
	
	//update a ingredient 
	public Ingredient updateReceipe(Ingredient ingredient) {
		return this.iRepo.save(ingredient);
	}
	
	//to get one ingredient if it exits in 
	public Ingredient getIngredientBYName(String name) {
		Ingredient target = this.iRepo.findByName(name);
		return target;
	}
	

}
