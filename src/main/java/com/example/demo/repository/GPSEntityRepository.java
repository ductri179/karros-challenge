package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.GPSEntity;

public interface GPSEntityRepository extends JpaRepository<GPSEntity, String> {

}
