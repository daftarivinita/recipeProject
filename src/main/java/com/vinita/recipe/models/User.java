package com.vinita.recipe.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name="users")
public class User {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max= 15)
    private String firstName;
    
    @NotBlank
    @Size(max= 15)
    private String lastName;
    
    @Email
    @NotBlank
    private String email;
    
    @Size(min = 8, message ="Password must be more than 8 character long!!!!!!!")
    private String password;
    
    @Transient
    private String confirmPassword;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Recipe> receipeCreated;
    

    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipeLiked;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "bookmarks", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipeSaves;
  
    
    
    
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }



	//constructor and getter and setters
    public User() {
    }

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	



	
	
	/*public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}*/
	
	public String getEmail() {
		return email;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	public List<Recipe> getReceipeCreated() {
		return receipeCreated;
	}
	public void setReceipeCreated(List<Recipe> receipeCreated) {
		this.receipeCreated = receipeCreated;
	}
	public List<Recipe> getRecipeLiked() {
		return recipeLiked;
	}
	public void setRecipeLiked(List<Recipe> recipeLiked) {
		this.recipeLiked = recipeLiked;
	}
	public List<Recipe> getRecipeSaves() {
		return recipeSaves;
	}
	public void setRecipeSaves(List<Recipe> recipeSaves) {
		this.recipeSaves = recipeSaves;
	}
	
	
    
    
    
}