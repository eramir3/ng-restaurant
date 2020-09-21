package com.restaurant.mapper;

import com.restaurant.dto.request.CookRequest;
import com.restaurant.dto.response.CookResponse;
import com.restaurant.entity.Cook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CookMapper {

    public Cook requestToEntity(CookRequest cookRequest) {
        Cook cook = new Cook();
        cook.setId(cookRequest.getId());
        cook.setFirstName(cookRequest.getFirstName());
        cook.setLastName(cookRequest.getLastName());
        cook.setBirthDate(cookRequest.getBirthDate());
        cook.setRemarks(cookRequest.getRemarks());
        return cook;
    }

    public CookResponse entityToResponse(Cook cook) {
        CookResponse cookResponse = new CookResponse();
        cookResponse.setId(cook.getId());
        cookResponse.setFirstName(cook.getFirstName());
        cookResponse.setLastName(cook.getLastName());
        cookResponse.setBirthDate(cook.getBirthDate());
        cookResponse.setRemarks(cook.getRemarks());
        return cookResponse;
    }

    public List<CookResponse> entityListToResponseList(Iterable<Cook> cookList) {
        List<CookResponse> cookResponseList = new ArrayList<>();
        for (Cook cook: cookList) {
            cookResponseList.add(this.entityToResponse(cook));
        }
        return cookResponseList;
    }
}
