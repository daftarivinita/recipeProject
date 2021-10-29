package com.vinita.recipe.controllers;

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
		System.out.println(resultFromApi);
		return "redirect:/";
	}
}
