package com.kyle.JavaMovingWars.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.JavaMovingWars.models.Gig;
import com.kyle.JavaMovingWars.services.GigServices;

@RestController
@CrossOrigin
public class APIController {
	@Autowired
	private GigServices gserv;
	
	@GetMapping("/gigs/")
	public List<Gig> index(){
		return gserv.allGigs();
	}
	@PostMapping("/gigs/")
	public Gig create(@Valid @RequestBody Gig g){
		return gserv.saveGig(g);
	}
	@DeleteMapping("/gigs/{id}")
	public String delete(@PathVariable("id")Long id) {
		return gserv.deleteGig(id);
	}
	@PutMapping("/gigs/{id}")
	public Gig update(@PathVariable("id")Long id,@Valid @RequestBody Gig g) {
		Gig g2=gserv.getGigById(id);
		g2.setTitle(g.getTitle());
		g2.setDescription(g.getDescription());
		return gserv.saveGig(g2);
	}
	@GetMapping("/gigs/{id}")
	public Gig view(@PathVariable("id")Long id) {
		return gserv.getGigById(id);
	}
}
