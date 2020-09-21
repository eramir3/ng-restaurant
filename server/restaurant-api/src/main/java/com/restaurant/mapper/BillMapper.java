package com.restaurant.mapper;

import com.restaurant.dto.request.BillRequest;
import com.restaurant.dto.response.BillResponse;
import com.restaurant.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillMapper {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private WaiterMapper waiterMapper;

    @Autowired
    private DiningTableMapper tableMapper;

    @Autowired
    private BillDetailMapper billDetailMapper;

    public Bill requestToEntity(BillRequest billRequest) {
        Bill bill = new Bill();
//        bill.setId(billRequest.getId());
//        bill.setClient(clientService.findOne(billRequest.getClientId()));
//        bill.setWaiter(waiterMapper.requestToEntity(billRequest.getWaiterRequest()));
//        bill.setDiningTable(tableMapper.requestToEntity(billRequest.getDiningTableRequest()));
        bill.setCreatedAt(billRequest.getCreatedAt());
        //bill.setBillDetailList(billRequest.getBillDetailList());
        return bill;
    }

    public BillResponse entityToResponse(Bill bill) {
        BillResponse billResponse = new BillResponse();
        billResponse.setId(bill.getId());
        billResponse.setClient(clientMapper.entityToResponse(bill.getClient()));
        billResponse.setWaiter(waiterMapper.entityToResponse(bill.getWaiter()));
        billResponse.setDiningTable(tableMapper.entityToResponse(bill.getDiningTable()));
        billResponse.setCreatedAt(bill.getCreatedAt());
        billResponse.setBillDetailList(billDetailMapper.entityListToResponseList(bill.getBillDetailList()));
        return billResponse;
    }

    public List<BillResponse> entityListToResponseList(Iterable<Bill> billList) {
        List<BillResponse> billResponseList = new ArrayList<>();
        for (Bill bill : billList) {
            billResponseList.add(this.entityToResponse(bill));
        }
        return billResponseList;
    }
}
