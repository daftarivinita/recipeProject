package com.vinita.recipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinita.recipe.models.Picture;
import com.vinita.recipe.models.Recipe;
import com.vinita.recipe.repositories.PictureRepository;



@Service
public class PictureService {
	@Autowired
	private PictureRepository pRepo;
    
//    //get all user
//	public List<Picture> userPictures(User user){
//		return this.pRepo.findAllByUser(user);
//	}
	
	public void uploadPic(String image_url, Recipe recipe) {
		Picture newPic = new Picture(image_url ,recipe);
		this.pRepo.save(newPic);
	}
	
	//get all picture
	public List<Picture> getAllPicture(){
		return this.pRepo.findAll();
	}
    
    
    
    
    
    // find picture by id
    public Picture findPictureById(Long id) {
    	return this.pRepo.findById(id).orElse(null);
    	
    	
    }
    
   
    
    
    
}
