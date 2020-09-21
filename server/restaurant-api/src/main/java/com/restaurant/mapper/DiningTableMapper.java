package com.restaurant.mapper;

import com.restaurant.dto.request.DiningTableRequest;
import com.restaurant.dto.response.DiningTableResponse;
import com.restaurant.entity.DiningTable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiningTableMapper {

    public DiningTable requestToEntity(DiningTableRequest tableRequest) {
        DiningTable table = new DiningTable();
        table.setId(tableRequest.getId());
        table.setNumDiners(tableRequest.getNumDiners());
        table.setLocation(tableRequest.getLocation());
        return table;
    }

    public DiningTableResponse entityToResponse(DiningTable table) {
        DiningTableResponse tableResponse = new DiningTableResponse();
        tableResponse.setId(table.getId());
        tableResponse.setNumDiners(table.getNumDiners());
        tableResponse.setLocation(table.getLocation());
        return tableResponse;
    }

    public List<DiningTableResponse> entityListToResponseList(Iterable<DiningTable> tableList) {
        List<DiningTableResponse> tableResponseList =  new ArrayList<>();
        for (DiningTable table : tableList) {
            tableResponseList.add(this.entityToResponse(table));
        }
        return tableResponseList;
    }
}
