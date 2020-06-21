package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.GPSEntity;
import com.example.demo.domain.UserEntity;
import com.example.demo.dto.GPSDTO;
import com.example.demo.dto.LatestGPSListDTO;
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

	@Override
	@Transactional
	public LatestGPSListDTO findLatest(int page, int size) {
		Pageable sortedByCreatedDate = new PageRequest(page, size, new Sort(Direction.DESC, "createdOn"));
		Page<GPSEntity> findAll = gpsEntityRepository.findAll(sortedByCreatedDate);
		
		List<GPSDTO> data = null;
		if (findAll.hasContent()) {
			data = findAll.getContent().stream().map(s -> {
				UserEntity userEntity = null;
				if (s.getRefUserId() != null) {
					userEntity = userEntityRepository.findOne(s.getRefUserId());
				}
				return new GPSDTO(s, userEntity);
			}).collect(Collectors.toList());
			size = data.size();
		} else {
			size = 0;
		}
		
		return new LatestGPSListDTO(page, size, data);
	}

	@Override
	@Transactional
	public GPSDTO findGPSById(Integer id) {
		if (id == null) {
			return null;
		}
		GPSEntity gps = gpsEntityRepository.findOne(id);
		UserEntity userEntity = null;
		if (gps != null && gps.getRefUserId() != null) {
			userEntity = userEntityRepository.findOne(gps.getRefUserId());
		}
		return new GPSDTO(gps, userEntity);
	}

}
