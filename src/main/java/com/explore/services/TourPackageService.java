package com.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.explore.domain.TourPackage;
import com.explore.repo.TourPackageRepository;

@Service
public class TourPackageService {
	private TourPackageRepository tourPackageRepository;
	
	/* generate constructor */	
	@Autowired
	public TourPackageService(TourPackageRepository tourPackageRepository) {
		super();
		this.tourPackageRepository = tourPackageRepository;
	}
	
	/* custom generate methods */
	public TourPackage createTourPackage(String code, String name) {
		if(!tourPackageRepository.exists(code)) { /* create one if it doesn't exist */
			tourPackageRepository.save(new TourPackage(code, name));
		}
		return null; /* if one exists, return a null*/
	}
	
	/* lookup all and total # the tour packages on the database(in Memory)*/
	public Iterable<TourPackage> lookup() {
		return tourPackageRepository.findAll(); /* invoke the find all method from the tour package repository */
	}
	
	public long total() {
		return tourPackageRepository.count();
	}
}
