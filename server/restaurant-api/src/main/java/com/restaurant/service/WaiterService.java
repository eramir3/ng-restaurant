package com.restaurant.service;

import com.restaurant.dto.request.WaiterRequest;
import com.restaurant.dto.response.WaiterResponse;
import com.restaurant.entity.Waiter;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.mapper.WaiterMapper;
import com.restaurant.projection.waiter.WaitersTotalBilled;
import com.restaurant.repository.IWaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private IWaiterRepository waiterRepository;

    @Autowired
    private WaiterMapper waiterMapper;

    public WaiterResponse save(WaiterRequest waiterRequest) {
        Waiter waiter = waiterMapper.requestToEntity(waiterRequest);
        waiterRepository.save(waiter);
        return waiterMapper.entityToResponse(waiter);
    }

    public WaiterResponse findOne(Long id) {

        Optional<Waiter> waiter = waiterRepository.findById(id);

        if (waiter.isPresent()) {
            WaiterResponse waiterResponse  = waiterMapper.entityToResponse(waiter.get());
            return waiterResponse;
        }
        else {
            throw new EntityNotFoundException("Waiter not found");
        }
    }

    public List<WaiterResponse> findAll() {
        Iterable<Waiter> waiters = waiterRepository.findAll();
        List<WaiterResponse> waiterResponseList = waiterMapper.entityListToResponseList(waiters);

        if (waiterResponseList.isEmpty()) {
            throw new EntityNotFoundException("Waiters not found");
        }
        return waiterMapper.entityListToResponseList(waiters);
    }

    public void deleteById(Long id) {
        WaiterResponse waiterResponse = findOne(id);
        waiterRepository.deleteById(waiterResponse.getId());
    }

    public List<WaitersTotalBilled> getTotalBilledByWaiter(String year) {
        year = "2020";
        List<WaitersTotalBilled> waitersList = waiterRepository.getTotalBilledByWaiter(year);
        return waitersList;
    }
}
