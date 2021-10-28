package com.vinita.recipe.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.recipe.models.Ingredient;








@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
	boolean existsByName(String name);
	Ingredient findByName(String name);
	
	
}
