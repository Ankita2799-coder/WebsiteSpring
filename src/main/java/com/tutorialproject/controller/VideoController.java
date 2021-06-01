package com.tutorialproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.model.Cards;
import com.tutorialproject.model.Video;
import com.tutorialproject.repository.CardRepo;
import com.tutorialproject.repository.VideoRepo;

@RestController
@CrossOrigin(origins = "*")
public class VideoController
{
	@Autowired
	VideoRepo vrepo;
	@Autowired
	CardRepo crepo;
	
	@GetMapping("/videos/{id}")
	public List<Video> getCards(@PathVariable("id") String id)
	{
//		System.out.println(id + "abc");
		Optional<Cards> card=crepo.findById(Integer.parseInt(id));
		List<Video> li=vrepo.findByCard(card);
		return li;
		
	}
	
	

}
