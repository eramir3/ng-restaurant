package com.restaurant.service;

import com.restaurant.dto.request.CookRequest;
import com.restaurant.dto.response.CookResponse;
import com.restaurant.entity.Cook;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.mapper.CookMapper;
import com.restaurant.repository.ICookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CookService {

    @Autowired
    private ICookRepository cookRepository;

    @Autowired
    private CookMapper cookMapper;

    public CookResponse save(CookRequest cookRequest) {
        Cook cook = cookMapper.requestToEntity(cookRequest);
        cookRepository.save(cook);
        return cookMapper.entityToResponse(cook);
    }

    public CookResponse findOne(Long id) {

        Optional<Cook> cook = cookRepository.findById(id);

        if (cook.isPresent()) {
            CookResponse cookResponse  = cookMapper.entityToResponse(cook.get());
            return cookResponse;
        }
        else {
            throw new EntityNotFoundException("Cook not found");
        }
    }

    public List<CookResponse> findAll() {
        Iterable<Cook> cooks = cookRepository.findAll();
        List<CookResponse> cookResponseList = cookMapper.entityListToResponseList(cooks);

        if (cookResponseList.isEmpty()) {
            throw new EntityNotFoundException("Cooks not found");
        }
        return cookMapper.entityListToResponseList(cooks);
    }

    public void deleteById(Long id) {
        CookResponse cookResponse = findOne(id);
        cookRepository.deleteById(cookResponse.getId());
    }
}
