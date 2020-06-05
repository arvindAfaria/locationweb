package com.aditya.location.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aditya.location.entities.Location;
import com.aditya.location.repos.LocationRepository;
import com.aditya.location.service.LocationService;
import com.aditya.location.util.EmailUtil;
import com.aditya.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired
	LocationService service;

	@Autowired
	LocationRepository repository;

	@Autowired
	EmailUtil emailUtil;
	
    @Autowired 
	ReportUtil reportUtil;
	 
	@Autowired
	ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

		Location locationSaved = service.saveLocation(location);
		String msg = "Location saved with id : " + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("cloudstrifetesting@gmail.com", "Location Saved",
				"Location Saved Successfully and about to send Response");
		return "createLocation";
	}

	@RequestMapping("displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		// This will hit the db
		// Location location = service.getLocationById(id);

		// This will avoid hitting db
		Location location = new Location();
		location.setId(id);

		service.deleteLocation(location);

		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations", locations);

		return "displayLocations";
	}

	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}

	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

		service.updateLocation(location);
		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("/generateReport")
	public String genrateReport() {

		String path = sc.getRealPath("/");

		List<Object[]> data = repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";
	}

}
