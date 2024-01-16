package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ImageData;

public interface ImageRepository extends JpaRepository<ImageData, Long>{

	Optional<ImageData> findByName(String name);
	
}
