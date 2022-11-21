package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Image;

@Component
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
