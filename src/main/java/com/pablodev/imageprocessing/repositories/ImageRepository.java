package com.pablodev.imageprocessing.repositories;

import com.pablodev.imageprocessing.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {


}
