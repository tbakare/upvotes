package com.upvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upvotes.domain.Feature;

public interface FeatureRepository extends JpaRepository<Feature,Integer>{

}
