package com.vinita.recipe.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="ingredients")
public class Ingredient {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique = true)         //nullable=false
	private String name;
	
	
   
	
	
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
	private List<IngredientQuantity> ingrediants;
	
	
	
	public Ingredient() {
	}

	
	public List<IngredientQuantity> getIngrediants() {
		return ingrediants;
	}


	public void setIngrediants(List<IngredientQuantity> ingrediants) {
		this.ingrediants = ingrediants;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toLowerCase();
	}
}
