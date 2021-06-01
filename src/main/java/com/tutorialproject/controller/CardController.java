package com.tutorialproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialproject.model.Cards;
import com.tutorialproject.repository.CardRepo;

@RestController
@CrossOrigin(origins = "*")
public class CardController 
{
	@Autowired
	CardRepo cardrepo;
	@GetMapping("/cards")
	public List<Cards> getCards()
	{
		List<Cards> li=cardrepo.findAll();
		return li;
		
	}
}
