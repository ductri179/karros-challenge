package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.domain.GPSEntity;

public interface GPSEntityRepository extends PagingAndSortingRepository<GPSEntity, Integer> {

}
