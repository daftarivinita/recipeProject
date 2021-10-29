package com.vinita.recipe.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name="recipes")
public class Recipe {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String prepTime;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String steps;
    
	//one user create a receipe
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

	@OneToMany(mappedBy = "recipe")
	private List<IngredientQuantity> ingrediants = new ArrayList<>();
	
	@OneToMany(mappedBy="recipe", fetch = FetchType.LAZY)
    private List<Picture> pictures;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes", 
        joinColumns = @JoinColumn(name = "recipe_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userLiked;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "bookmarks", 
        joinColumns = @JoinColumn(name = "recipe_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userSaves;
	
	 @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }



	public Recipe() {
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


	public String getPrepTime() {
		return prepTime;
	}


	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}


	public String getSteps() {
		return steps;
	}


	public void setSteps(String steps) {
		this.steps = steps;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<Picture> getPictures() {
		return pictures;
	}


	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}


	public List<User> getUserLiked() {
		return userLiked;
	}


	public void setUserLiked(List<User> userLiked) {
		this.userLiked = userLiked;
	}


	public List<User> getUserSaves() {
		return userSaves;
	}


	public void setUserSaves(List<User> userSaves) {
		this.userSaves = userSaves;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	


//	public String getImage_url() {
//		return image_url;
//	}
//
//
//	public void setImage_url(String image_url) {
//		this.image_url = image_url;
//	}
	
	
}