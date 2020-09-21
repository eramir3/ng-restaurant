package com.restaurant.mapper;

import com.restaurant.dto.request.WaiterRequest;
import com.restaurant.dto.response.WaiterResponse;
import com.restaurant.entity.Waiter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WaiterMapper {

    public Waiter requestToEntity(WaiterRequest waiterRequest) {
        Waiter waiter = new Waiter();
        waiter.setId(waiterRequest.getId());
        waiter.setFirstName(waiterRequest.getFirstName());
        waiter.setLastName(waiterRequest.getLastName());
        waiter.setBirthDate(waiterRequest.getBirthDate());
        waiter.setRemarks(waiterRequest.getRemarks());
        return waiter;
    }

    public WaiterResponse entityToResponse(Waiter waiter) {
        WaiterResponse waiterResponse = new WaiterResponse();
        waiterResponse.setId(waiter.getId());
        waiterResponse.setFirstName(waiter.getFirstName());
        waiterResponse.setLastName(waiter.getLastName());
        waiterResponse.setBirthDate(waiter.getBirthDate());
        waiterResponse.setRemarks(waiter.getRemarks());
        return waiterResponse;
    }

    public List<WaiterResponse> entityListToResponseList(Iterable<Waiter> waiterList) {
        List<WaiterResponse> waiterResponseList = new ArrayList<>();
        for (Waiter waiter: waiterList) {
            waiterResponseList.add(this.entityToResponse(waiter));
        }
        return waiterResponseList;
    }
}
