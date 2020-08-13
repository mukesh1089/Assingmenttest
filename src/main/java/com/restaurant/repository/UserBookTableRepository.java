package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.UserBookTable;

@Repository
public interface UserBookTableRepository extends JpaRepository<UserBookTable, Integer> {

	@Query("SELECT u FROM UserBookTable u WHERE u.restaurantTableName = ?1")
	List<UserBookTable> findAllBookTableByUser(String restaurantTableName);
}