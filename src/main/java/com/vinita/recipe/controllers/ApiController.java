package com.vinita.recipe.controllers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class ApiController {
	String searchUrl = "http://www.themealdb.com/api/json/v1/1/filter.php?i";
	
	@PostMapping("/recipe/search/ingredient")
	public String searchcity(@RequestParam("name") String name, Model myModel) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.get(searchUrl +"&q={name}").routeParam("name", name).asJson();
		JSONObject resultFromApi = response.getBody().getObject();
		//System.out.println(resultFromApi);
		JSONArray meals =resultFromApi.getJSONArray("meals");
		//System.out.println(meals);
		//it cannot parse jsonarray object list so we are making new array
		ArrayList<JSONObject> resultFromMeals = new ArrayList<JSONObject>();
		for(int i = 0; i<meals.length(); i++) {
			resultFromMeals.add(meals.getJSONObject(i));
		}
		System.out.println(resultFromMeals);
		myModel.addAttribute("meals",resultFromMeals);
		return "api.jsp";
	}
	
	
	//thinking to fetch id and redirect to youtube link
//	@GetMapping("/recipe/{id}/api")
//	public String recipeDetails(@PathVariable("id") String id) throws UnirestException {
//		HttpResponse<JsonNode> response = Unirest.get("http://www.themealdb.com/api/json/v1/1/lookup.php?i" +"&q={id}").routeParam("id", id).asJson();
//		
//		System.out.println(response);
//		return "redirect:/";
//	}
}
