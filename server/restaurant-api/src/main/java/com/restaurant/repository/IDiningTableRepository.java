package com.restaurant.repository;

import com.restaurant.entity.DiningTable;
import org.springframework.data.repository.CrudRepository;

public interface IDiningTableRepository extends CrudRepository<DiningTable, Long> {
}
