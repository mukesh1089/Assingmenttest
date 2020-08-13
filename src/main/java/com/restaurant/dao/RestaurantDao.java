package com.restaurant.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restaurant.entity.UserBookTable;
import com.restaurant.repository.UserBookTableRepository;
import com.restaurant.request.BookRequest;

@Component
public class RestaurantDao {

	@Autowired
	private UserBookTableRepository userBookTableRepository;

	public boolean bookTable(BookRequest bookRequest) {
		boolean status = false, same = false;
		List<UserBookTable> userBookTables = userBookTableRepository.findAllBookTableByUser(bookRequest.getTableName());
		UserBookTable userBookTable = new UserBookTable();
		userBookTable.setUserId(bookRequest.getUserId());
		userBookTable.setRestaurantTableName(bookRequest.getTableName());
		userBookTable.setBookingDateTime(convertStringToDate(bookRequest.getBookingDate(), bookRequest.getTime()));
		if(userBookTables != null && !userBookTables.isEmpty()) {
			for (UserBookTable userbook : userBookTables) {
				if (userbook.getBookingDateTime().compareTo(userBookTable.getBookingDateTime()) == 0) {
					same = true;
				}
			}
			if(!same) {
				userBookTableRepository.save(userBookTable);
				status = true;
			}
		} else {
		
			userBookTableRepository.save(userBookTable);
			status = true;
		}
		return status;
	}

	Date convertStringToDate(String strDate, String time) {
		if (strDate != null && time != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			Date date = null;
			try {
				date = sdf.parse(strDate + " " + time);
				Calendar calender = Calendar.getInstance();
				calender.setTime(date);
				date = calender.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		}
		return null;

	}

}
