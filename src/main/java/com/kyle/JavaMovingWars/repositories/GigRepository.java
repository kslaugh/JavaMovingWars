package com.kyle.JavaMovingWars.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kyle.JavaMovingWars.models.Gig;

@Repository
public interface GigRepository extends CrudRepository<Gig, Long> {
	List<Gig> findAll();
}
