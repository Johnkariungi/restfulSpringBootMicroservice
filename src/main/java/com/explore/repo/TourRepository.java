package com.explore.repo;

import org.springframework.data.repository.CrudRepository;

import com.explore.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Integer> {

}
