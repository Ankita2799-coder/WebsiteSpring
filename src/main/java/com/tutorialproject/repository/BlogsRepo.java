package com.tutorialproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorialproject.model.BlogsModel;
@Repository
public interface BlogsRepo extends JpaRepository<BlogsModel, Integer>{

	BlogsModel findByBlogid(String id);

}
