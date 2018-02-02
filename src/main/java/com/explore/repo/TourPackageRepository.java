package com.explore.repo;

import org.springframework.data.repository.CrudRepository;

import com.explore.domain.TourPackage;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
	TourPackage findByName(String name);
}
