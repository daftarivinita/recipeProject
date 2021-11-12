package com.vinita.recipe.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vinita.recipe.services.UserService;

@Controller
public class ApiController {
	@Autowired
	public UserService uService;
	String searchUrl = "http://www.themealdb.com/api/json/v1/1/filter.php?i";
	
	@PostMapping("/recipe/search/ingredient")
	public String searchcity(@RequestParam("name") String name, Model myModel,  HttpSession session, RedirectAttributes redirectAttributes) throws UnirestException {
		//System.out.println(searchUrl +"={name}");
//		HttpResponse<JsonNode> response = Unirest.get(searchUrl +"&q={name}").routeParam("name", name).asJson();
		HttpResponse<JsonNode> response = Unirest.get(searchUrl +"={name}").routeParam("name", name).asJson();
		//System.out.println(response);
		JSONObject resultFromApi = response.getBody().getObject();
		System.out.println(resultFromApi);
		System.out.println(resultFromApi.get("meals"));
		if(resultFromApi.get("meals").equals(null)) {
			redirectAttributes.addFlashAttribute("errorFromApi", "No recipe Found with this Ingredient!");
			return "redirect:/";
		}
		JSONArray meals =resultFromApi.getJSONArray("meals");
		
		//System.out.println(meals);
		//it cannot parse jsonarray object list so we are making new array
		ArrayList<JSONObject> resultFromMeals = new ArrayList<JSONObject>();
		for(int i = 0; i<meals.length(); i++) {
			resultFromMeals.add(meals.getJSONObject(i));
		}
		//System.out.println(resultFromMeals);
		if (session.getAttribute("user__id") == null) {
			myModel.addAttribute("meals",resultFromMeals);
			return "api.jsp";
		}else {
		myModel.addAttribute("user", this.uService.findUserById((Long)session.getAttribute("user__id")));
		myModel.addAttribute("meals",resultFromMeals);
		return "api.jsp";
		}
	}
	
	
	//thinking to fetch id and redirect to youtube link
	@GetMapping("/recipe/{id}/api")
	public String recipeDetails(@PathVariable("id") String id) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.get("http://www.themealdb.com/api/json/v1/1/lookup.php?i" +"={id}").routeParam("id", id).asJson();
		JSONObject resultFromApi = response.getBody().getObject();
		
		JSONArray meals =resultFromApi.getJSONArray("meals");
		
		ArrayList<JSONObject> resultFromMeals = new ArrayList<JSONObject>();
		for(int i = 0; i<meals.length(); i++) {
			resultFromMeals.add(meals.getJSONObject(i));
		}
		JSONObject youTube = resultFromMeals.get(0);
		String result = youTube.getString("strYoutube");
		System.out.println(youTube.getString("strYoutube"));
		return "redirect:" + result;
	}
}
