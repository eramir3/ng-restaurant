package com.restaurant.repository;

import com.restaurant.entity.Bill;
import org.springframework.data.repository.CrudRepository;

public interface IBillRepository extends CrudRepository<Bill, Long> {
}
