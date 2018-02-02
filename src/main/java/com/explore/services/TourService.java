package com.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.explore.domain.Difficulty;
import com.explore.domain.Region;
import com.explore.domain.Tour;
import com.explore.domain.TourPackage;
import com.explore.repo.TourPackageRepository;
import com.explore.repo.TourRepository;

@Service
public class TourService {
	/* inject the tour repositories */
	private TourPackageRepository tourPackageRepository;
	private TourRepository tourRepository;
	
	/* generate constructor */
	@Autowired
	public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
		super();
		this.tourPackageRepository = tourPackageRepository;
		this.tourRepository = tourRepository;
	}
	
	/* method to create a new tour passing the attributes of a tour */
	public Tour createTour(String title, String description, String blub, Integer price, String duration,
			String bullets, String keywords, String tourPackageName, Difficulty difficulty, Region region) {
		/* Lookup the tour package from the DB */
		TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName);
		if (tourPackage == null) {
			throw new RuntimeException("Tour package does not exist: " + tourPackageName);
		}
		/* create a new tour object and save it to the database. */
		return tourRepository.save( new Tour(title, description, blub, price, duration, bullets, 
				keywords, tourPackage, difficulty, region));
	}
	
	public Iterable<Tour> lookup() {
		return tourRepository.findAll();
	}
	
	public long total() {
		return tourRepository.count();
	}
}
