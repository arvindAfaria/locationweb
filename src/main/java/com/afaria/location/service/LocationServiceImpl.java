package com.afaria.location.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afaria.location.entities.Location;
import com.afaria.location.repos.LocationRepository;
import com.afaria.location.util.ReportUtilImpl;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repository;
	
	@Autowired
	ReportUtilImpl reportUtil;

	@Override
	public Location saveLocation(Location location) {
		return repository.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		return repository.save(location);
	}

	@Override
	public void deleteLocation(Location location) {
		repository.delete(location);
	}

	@Override
	public Location getLocationById(int id) {
		return repository.findById(id).get();
	}

	
	
	@Override
	public List<Location> getAllLocation() {
		return repository.findAll();
	}

	public LocationRepository getRepository() {
		return repository;
	}

	public void setRepository(LocationRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean getReport() {
		System.out.println("----debuf statement:   inside getreport ---");
		return true;
	}

}
