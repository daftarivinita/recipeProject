package com.vinita.recipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vinita.recipe.models.IngredientQuantity;



public interface IngredientQuantityRepository extends CrudRepository<IngredientQuantity, Long>{
	

	@Transactional
	List<IngredientQuantity> deleteByRecipeId(Long recipeId);
}

