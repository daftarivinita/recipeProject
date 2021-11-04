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
	
//	//get all product
//	public List<Ingredient> getAllIngredient(){
//		return this.iRepo.findAll();
//	}
//	
//	//find product that are not in specific category
//		public List<Product> getProductNotInCategory(Category category){
//			return this.Repo.findByCategoriesNotContains(category);
//		}
		
//	//setting many to many
//	public void addCategoryToProduct(Category category, Product product) {
//		product.getCategories().add(category);
//		this.pRepo.save(product);
//	}
//		// adding likes to product from user
//	public void userLikesProduct(User user, Product product) {
//		product.getUserLiked().add(user);
//		this.pRepo.save(product);
//	}
//	//removing likes from product
//	public void userDisLikesProduct(User user, Product product) {
//		product.getUserLiked().remove(user);
//		this.pRepo.save(product);
//	}
}
