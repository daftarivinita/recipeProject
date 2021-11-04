package com.vinita.recipe.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinita.recipe.models.Ingredient;
import com.vinita.recipe.models.IngredientQuantity;
import com.vinita.recipe.models.Picture;
import com.vinita.recipe.models.Recipe;
import com.vinita.recipe.models.User;
import com.vinita.recipe.services.IngredientQuantityService;
import com.vinita.recipe.services.IngredientService;
import com.vinita.recipe.services.PictureService;
import com.vinita.recipe.services.ReceipeService;
import com.vinita.recipe.services.UserService;


@Controller
public class RecipeController {
	
	@Autowired
	public UserService uService;
	
	@Autowired
	public ReceipeService rService;
	
	@Autowired
	public IngredientService iService;
	
	@Autowired
	public IngredientQuantityService qService;
	
	@Autowired
	public PictureService pService;
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
	
	
	//for creating a recipe
	@GetMapping("/recipe")
	public String getRecipeForm(Model myModel,@ModelAttribute("recipe") Recipe recipe, HttpSession session, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user__id") == null) {
			redirectAttributes.addFlashAttribute("errors", "PLEASE LOGIN TO Add Recipe!");
			return "redirect:/user";
		}
		myModel.addAttribute("allRecipe", this.rService.getAllReceipe());
		return "new.jsp";
	}
	
	//home page
	@GetMapping("/")
	public String welcomePage(Model myModel,HttpSession session) {
		if (session.getAttribute("user__id") == null) {
			myModel.addAttribute("allRecipe", this.rService.getAllReceipe());
			//System.out.println(this.rService.topTwoNew());
			myModel.addAttribute("newRecipes", this.rService.topTwoNew());
			myModel.addAttribute("topLiked", this.rService.topTwoLiked());
			return "dashboard.jsp";
		} else {
		myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		myModel.addAttribute("allRecipe", this.rService.getAllReceipe());
		myModel.addAttribute("newRecipes", this.rService.topTwoNew());
		myModel.addAttribute("topLiked", this.rService.topTwoLiked());
		return "dashboard.jsp";
	}
	}

	
//	//to create ingrediant
//	@PostMapping("/new/ingredient")
//    public String createIngrediant(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/food/new.jsp";
//        } else {
//            this.iService.createIngrediant(ingredient);
//            return "redirect:/";
//        }
//     
//    }
	//to create ingrediant
		@PostMapping("/recipe")
	    public String createReciepe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, HttpSession session) {
			
	        if (result.hasErrors()) {
	            return "new.jsp";
	        } else {
	        	recipe.setUser(this.uService.findUserById((Long)session.getAttribute("user__id")));
	            this.rService.createReceipe(recipe);
	            Long id = recipe.getId();
	            return "redirect:/recipe/addPicture/"+ id;
	        }
	     
	    }
		
		//after creating recipe user will land in this page to either upload picture or edit
		//for picture upload and edit show page
		@GetMapping(value = {"/recipe/addPicture/{id}", "/recipe/{id}/edit"})
		public String showPictureAddPage(@PathVariable("id") Long id, Model myModel, @ModelAttribute("recipe") Recipe recipe,HttpSession session, RedirectAttributes redirectAttributes) {
			if(session.getAttribute("user__id") == null) {
				redirectAttributes.addFlashAttribute("errors", "PLEASE LOGIN TO EDIT OR UPDATE!");
				return "redirect:/user";
			}
			myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
			Recipe target = this.rService.getReceipe(id);
			myModel.addAttribute("recipe", target);
			return "addPicture.jsp";
			
		}
		
		
		//post mapping for edit
		@PostMapping("/recipe/{id}/edit")
		public String updateRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, Model myModel,@PathVariable("id") Long id,HttpSession session ) {
			
			if(result.hasErrors()) {
				myModel.addAttribute("recipe", this.rService.getReceipe(id));
				
				return "addPicture.jsp";
			}
			else {
				myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
				recipe.setUser(this.uService.findUserById((Long)session.getAttribute("user__id")));
				//recipe.setUser(this.uService.);
				this.qService.deleteIngedientQuantitiesByRecipe(recipe.getId());
				this.rService.updateReceipe(recipe);
	            return "redirect:/";
			}
		
		}
		
		
		
		//post mapping for picture upload
		@PostMapping("/recipe/{id}/picture")
		public String appingPicture(@PathVariable("id") Long id, @RequestParam("pic") MultipartFile file, RedirectAttributes redirectAtt) {
			Recipe target = this.rService.getReceipe(id);
			try {
				//get the file and it throw into server folder
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
				//GEt URl of the file we just uploaded
				String url = "/img/" + file.getOriginalFilename();
//				target.setImage_url(url);
//				System.out.println(target.getImage_url());
				this.pService.uploadPic(url, target);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/recipe";
			
		}
		
		@GetMapping("recipe/{id}")
		public String showRecipe(@PathVariable("id") Long id, Model myModel, HttpSession session) {
			Recipe target = this.rService.getReceipe(id);
			if (session.getAttribute("user__id") == null) {
				myModel.addAttribute("recipe", target);
				return "detail.jsp";
			}else {
				myModel.addAttribute("recipe", target);

				myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
				return "detail.jsp";
			}
			
		}

		
		//for likes
		@GetMapping("/recipe/{id}/like")
		public String userToLike(@PathVariable("id") Long id, HttpSession session) {
			User targetUser= this.uService.findUserById((Long)session.getAttribute("user__id"));
			Recipe targetRecipe = this.rService.getReceipe(id);
			this.rService.userLikesRecipe(targetUser, targetRecipe);
			return "redirect:/recipe/" + id;
		}
		
		//for dislikes
		@GetMapping("/recipe/{id}/dislike")
		public String userToDisLike(@PathVariable("id") Long id, HttpSession session) {
			User targetUser= this.uService.findUserById((Long)session.getAttribute("user__id"));
			Recipe targetRecipe = this.rService.getReceipe(id);
			this.rService.userDisLikesRecipe(targetUser, targetRecipe);
			return "redirect:/recipe/" + id;
		}
		//for bookmarks
		@GetMapping("/recipe/{id}/bookmark")
		public String userToBookmark(@PathVariable("id") Long id, HttpSession session) {
			User targetUser= this.uService.findUserById((Long)session.getAttribute("user__id"));
			Recipe targetRecipe = this.rService.getReceipe(id);
			this.rService.userSaveRecipe(targetUser, targetRecipe);
			return "redirect:/recipe/" + id;
		}
		
		//for removing bookmarks
		@GetMapping("/recipe/{id}/unbookmark")
		public String userToDisBookmark(@PathVariable("id") Long id, HttpSession session) {
			User targetUser= this.uService.findUserById((Long)session.getAttribute("user__id"));
			Recipe targetRecipe = this.rService.getReceipe(id);
			this.rService.userDisSaveRecipe(targetUser, targetRecipe);
			return "redirect:/recipe/" + id;
		}
		
		
		//something wrong with delete method
		//delete a recipe
		@PostMapping("/recipe/{id}/delete")
		public String deleteRecipe(@PathVariable("id") Long id, HttpSession session) {
			Recipe target = this.rService.getReceipe(id);
			List<Picture> allPicture= target.getPictures();
			if(allPicture.size() > 0) {
			for (Picture pic : allPicture) {
				this.pService.deletePicture(pic.getId());
				}
			}
			
			List<IngredientQuantity> allIngredient = target.getIngrediants();
			if(allIngredient.size() >0) {
				for(IngredientQuantity ing: allIngredient) {
				this.qService.deleteIngredientQuantity(ing.getId());
				}
			}
			this.rService.deleteReceipe(id);
			return "redirect:/";
			
		}
			
			
			
			
		@PostMapping("/recipe/search")
		public String recipeSearch(@RequestParam("recipe") String recipe, Model myModel, RedirectAttributes redirectAttributes, HttpSession session) {
			String targetRecipe= (String) recipe;
			List<Recipe> recipesearch = this.rService.findAllByTitle(targetRecipe);
			if(recipesearch.size() == 0) {
				redirectAttributes.addFlashAttribute("searchError", "This Recipe not found!");
				return "redirect:/";
			}
			if (session.getAttribute("user__id") == null) {
			
			myModel.addAttribute("allRecipe", recipesearch);
			return "search.jsp";
			} else {
				myModel.addAttribute("allRecipe", recipesearch);
				myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
				return "search.jsp";
			}
			
		}
		
		@PostMapping("/recipe/search/ingredient/api")
		public String recipeSearchFromingredient(@RequestParam("name") String name, Model myModel, RedirectAttributes redirectAttributes, HttpSession session) {
			System.out.println(name);
			String nameToFind = name.toLowerCase();

			Ingredient targetIng = this.iService.getIngredientBYName(nameToFind);
			System.out.println(targetIng);

			if(targetIng == null) {
				redirectAttributes.addFlashAttribute("searchError", "This Recipe not found with this Ingredient!");
			}
			
			IngredientQuantity ing = this.qService.getIngredientQuantity(targetIng);
			List<Recipe> recipesearch = this.rService.findRecipeWithIngrediant(ing);
			System.out.println(recipesearch);

			if (session.getAttribute("user__id") == null) {
			
			myModel.addAttribute("allRecipe", recipesearch);
			return "search.jsp";
			} else {
				myModel.addAttribute("allRecipe", recipesearch);
				myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
				return "search.jsp";
			}
			
		}

}
