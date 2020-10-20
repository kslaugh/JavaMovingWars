package com.kyle.JavaMovingWars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.JavaMovingWars.models.Gig;
import com.kyle.JavaMovingWars.repositories.GigRepository;

@Service
public class GigServices {
	@Autowired
	private GigRepository repo;
	
	public List<Gig> allGigs(){
		return repo.findAll();
	}
	public String deleteGig(Long id) {
		repo.deleteById(id);
		return "ID "+id+" successfully deleted!";
	}
	public Gig getGigById(Long id) {
		return repo.findById(id).get();
	}
	public Gig saveGig(Gig g) {
		return repo.save(g);
	}
}
