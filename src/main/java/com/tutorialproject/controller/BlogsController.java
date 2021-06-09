package com.tutorialproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.model.BlogsModel;
import com.tutorialproject.repository.BlogsRepo;
@RestController
@CrossOrigin(origins = "*")
public class BlogsController
{
	@Autowired
	BlogsRepo blogrepo;
	
	@GetMapping("blogs")
	public List<BlogsModel> getBlogs()
	{
		List<BlogsModel> list=blogrepo.findAll();
		return list;
	}
	
	@GetMapping("blogs/{id}")
	public BlogsModel getBlogPost(@PathVariable("id") String id)
	{
		return blogrepo.findByBlogid(id);
		
	}

}
