package com.restaurant.repository;

import com.restaurant.entity.Waiter;
import com.restaurant.projection.client.ProfitableClients;
import com.restaurant.projection.waiter.WaitersTotalBilled;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWaiterRepository extends CrudRepository<Waiter, Long> {

    @Query(value = "SELECT first_name as firstName, last_name as lastName, total_billed as totalBilled," +
            " january, february, march, april, may, june, july, august, september, october, november, december" +
            " FROM get_waiters_total_billed(:year)", nativeQuery = true)
    List<WaitersTotalBilled> getTotalBilledByWaiter(@Param("year") String year);
}
