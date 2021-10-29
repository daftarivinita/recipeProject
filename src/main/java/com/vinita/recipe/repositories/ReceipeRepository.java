package com.vinita.recipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.vinita.recipe.models.Recipe;




@Repository
public interface ReceipeRepository extends CrudRepository<Recipe, Long>{
	List<Recipe> findAll();
	//List<Product> findByCategoriesNotContains(Category category);
	List<Recipe> findByTitleContaining(String title);
	List<Recipe> findTop4ByOrderByUserLikedDesc();
	List<Recipe> findTop4ByOrderByCreatedAtDesc();
}
