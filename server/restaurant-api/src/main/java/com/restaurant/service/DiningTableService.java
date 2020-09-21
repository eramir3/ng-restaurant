package com.restaurant.service;

import com.restaurant.dto.request.DiningTableRequest;
import com.restaurant.dto.response.DiningTableResponse;
import com.restaurant.entity.DiningTable;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.mapper.DiningTableMapper;
import com.restaurant.repository.IDiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {

    @Autowired
    private IDiningTableRepository diningTableRepository;

    @Autowired
    private DiningTableMapper diningTableMapper;

    public DiningTableResponse save(DiningTableRequest diningTableRequest) {
        DiningTable diningTable = diningTableMapper.requestToEntity(diningTableRequest);
        diningTableRepository.save(diningTable);
        return diningTableMapper.entityToResponse(diningTable);
    }

    public DiningTableResponse findOne(Long id) {

        Optional<DiningTable> diningTable = diningTableRepository.findById(id);

        if (diningTable.isPresent()) {
            DiningTableResponse diningTableResponse  = diningTableMapper.entityToResponse(diningTable.get());
            return diningTableResponse;
        }
        else {
            throw new EntityNotFoundException("Table not found");
        }
    }

    public List<DiningTableResponse> findAll() {
        Iterable<DiningTable> diningTables = diningTableRepository.findAll();
        List<DiningTableResponse> diningTableResponseList = diningTableMapper.entityListToResponseList(diningTables);

        if (diningTableResponseList.isEmpty()) {
            throw new EntityNotFoundException("Tables not found");
        }
        return diningTableMapper.entityListToResponseList(diningTables);
    }

    public void deleteById(Long id) {
        DiningTableResponse diningTableResponse = findOne(id);
        diningTableRepository.deleteById(diningTableResponse.getId());
    }
}
