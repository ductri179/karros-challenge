package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.GPSEntity;
import com.example.demo.domain.UserEntity;
import com.example.demo.repository.GPSEntityRepository;
import com.example.demo.repository.UserEntityRepository;

import io.jenetics.jpx.GPX;

@Service
public class GPSServiceImpl implements GPSService {
	
	private final GPSEntityRepository gpsEntityRepository;
	private final UserEntityRepository userEntityRepository;
	
	public GPSServiceImpl(GPSEntityRepository gpsEntityRepository, UserEntityRepository userEntityRepository) {
		this.gpsEntityRepository = gpsEntityRepository;
		this.userEntityRepository = userEntityRepository;
	}

	/**
	 *
	 * Save GPX info into DB
	 *
	 */
	@Override
	public void save(GPX gpx, int userId) {
		
		// Init GPS Entity by given GPX Object
		GPSEntity gpsEntity = new GPSEntity(gpx);

		// Set user id to GPS Entity
		UserEntity user = this.userEntityRepository.findOne(userId);
		if (user != null) {
			gpsEntity.setRefUserId(userId);
		}
		
		this.gpsEntityRepository.save(gpsEntity);
	}

}
