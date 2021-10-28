package com.vinita.recipe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinita.recipe.models.Picture;






@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
 //  List<Picture> findAllByUser(User user);
   List<Picture> findAll();
}
