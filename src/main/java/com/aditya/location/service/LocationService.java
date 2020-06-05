package com.aditya.location.service;

import java.util.List;

import com.aditya.location.entities.Location;

public interface LocationService {

	Location saveLocation(Location location);

	Location updateLocation(Location location);

	void deleteLocation(Location location);

	Location getLocationById(int id);

	List<Location> getAllLocation();
	
	boolean getReport();
	

}
