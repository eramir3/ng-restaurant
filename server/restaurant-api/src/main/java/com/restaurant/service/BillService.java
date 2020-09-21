package com.restaurant.service;

import com.restaurant.dto.request.BillDetailRequest;
import com.restaurant.dto.request.BillRequest;
import com.restaurant.dto.response.BillResponse;
import com.restaurant.entity.*;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.mapper.BillMapper;
import com.restaurant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private IWaiterRepository waiterRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IDiningTableRepository tableRepository;

    @Autowired
    private ICookRepository cookRepository;

    @Autowired
    private BillMapper billMapper;

    public BillResponse save(BillRequest billRequest) {

        Optional<Waiter> waiter = waiterRepository.findById(billRequest.getWaiterId());
        Optional<Client> client = clientRepository.findById(billRequest.getClientId());
        Optional<DiningTable> table = tableRepository.findById(billRequest.getDiningTableId());

        if (!waiter.isPresent() || !client.isPresent() || !table.isPresent()) {
            throw new EntityNotFoundException("Invalid values");
        }

        Bill bill = new Bill();
        bill.setWaiter(waiter.get());
        bill.setClient(client.get());
        bill.setDiningTable(table.get());
        bill.setCreatedAt(billRequest.getCreatedAt());

        List<BillDetail> billDetails = new ArrayList<>();
        for (BillDetailRequest billDetailRequest : billRequest.getBillDetailList()) {
            Optional<Cook> cook = cookRepository.findById(billDetailRequest.getCookId());

            if(!cook.isPresent()) {
                throw new EntityNotFoundException("Invalid values");
            }

            BillDetail billDetail = new BillDetail();
            billDetail.setBill(bill);
            billDetail.setCook(cook.get());
            billDetail.setDish(billDetailRequest.getDish());
            billDetail.setPrice(billDetailRequest.getPrice());
            billDetails.add(billDetail);
        }

        bill.setBillDetailList(billDetails);
        billRepository.save(bill);

        return billMapper.entityToResponse(bill);
    }

    public BillResponse findOne(Long id) {

        Optional<Bill> bill = billRepository.findById(id);

        if (bill.isPresent()) {
            BillResponse billResponse  = billMapper.entityToResponse(bill.get());
            return billResponse;
        }
        else {
            throw new EntityNotFoundException("Bill not found");
        }
    }

    public List<BillResponse> findAll() {
        Iterable<Bill> bills = billRepository.findAll();
        List<BillResponse> billResponseList = billMapper.entityListToResponseList(bills);

        if (billResponseList.isEmpty()) {
            throw new EntityNotFoundException("Bills not found");
        }
        return billMapper.entityListToResponseList(bills);
    }

    public void deleteById(Long id) {
        BillResponse billResponse = findOne(id);
        billRepository.deleteById(billResponse.getId());
    }
}
