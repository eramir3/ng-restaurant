package com.restaurant.repository;

import com.restaurant.entity.Cook;
import org.springframework.data.repository.CrudRepository;

public interface ICookRepository extends CrudRepository<Cook, Long> {
}
