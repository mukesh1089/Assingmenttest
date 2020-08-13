package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.dao.RestaurantDao;
import com.restaurant.request.BookRequest;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
		// Injecting dependency of RestaurantDao
	@Autowired
	private RestaurantDao restaurantDao;
	
	
	@PostMapping("/bookTable")
	public String bookTable(@RequestBody BookRequest bookRequest) {
		Boolean bookStatus = restaurantDao.bookTable(bookRequest);
		if(bookStatus) {
			return "Table booked successfully";
		} else {
			return "Booking failed due this table already occupied by another user";
		}
	}
}
