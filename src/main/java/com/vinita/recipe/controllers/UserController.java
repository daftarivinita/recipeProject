package com.vinita.recipe.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinita.recipe.models.User;
import com.vinita.recipe.services.UserService;
import com.vinita.recipe.validators.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	
	@GetMapping("")
	public String landing(@ModelAttribute("user") User user) {
		return "user.jsp";
		
	
	}
	@PostMapping("/loginToRegister")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "user.jsp";
		} else {
			User newUser = this.uService.registerUser(user);
			session.setAttribute("user__id",newUser.getId());
			return "redirect:/user/dashboard";
		}
		
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam("lemail") String email, @RequestParam("lpassword") String password, RedirectAttributes redirectAttribute) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttribute.addFlashAttribute("loginError", "Invalid Credentials");
			return "redirect:/user";
		}
		User userToLog = this.uService.getUserByEmail(email);
		session.setAttribute("user__id", userToLog.getId());
		return "redirect:/user/dashboard";
	}
//	@GetMapping("/landing")
//	public String landing(HttpSession session ) {
////		session.getAttribute("user__id", this.uService.findUserById(null));
//	}
	//tologout
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/dashboard")
	public String usertolog(Model mymodel, HttpSession session) {
		mymodel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		return "dashboard.jsp";
	}
	
	@GetMapping("/bookmark")
		public String allFavorite(HttpSession session, Model myModel, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user__id") == null) {
			redirectAttributes.addFlashAttribute("errors", "PLEASE LOGIN TO See your Favorite Recipe!");
			return "redirect:/user";
		}	
		User user = this.uService.findUserById((Long)session.getAttribute("user__id"));
			myModel.addAttribute("user",user);
			myModel.addAttribute("allRecipe", user.getRecipeSaves());
			return "bookmark.jsp";
		}
	}
	

