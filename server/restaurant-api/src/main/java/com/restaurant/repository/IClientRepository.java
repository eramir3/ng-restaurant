package com.restaurant.repository;

import com.restaurant.projection.client.ProfitableClients;
import com.restaurant.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT first_name as firstName, last_name as lastName, total_price as totalPrice " +
            "FROM get_profitable_clients()", nativeQuery = true)
    List<ProfitableClients> findProfitableClients();

}
