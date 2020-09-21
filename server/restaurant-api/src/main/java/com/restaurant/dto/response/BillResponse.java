package com.restaurant.dto.response;

import java.time.LocalDate;
import java.util.List;

public class BillResponse {

    private Long id;

    private ClientResponse client;

    private WaiterResponse waiter;

    private DiningTableResponse diningTable;

    private List<BillDetailResponse> billDetailList;

    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientResponse getClient() {
        return client;
    }

    public void setClient(ClientResponse clientResponse) {
        this.client = clientResponse;
    }

    public WaiterResponse getWaiter() {
        return waiter;
    }

    public void setWaiter(WaiterResponse waiterResponse) {
        this.waiter = waiterResponse;
    }

    public DiningTableResponse getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(DiningTableResponse diningTableResponse) {
        this.diningTable = diningTableResponse;
    }

    public List<BillDetailResponse> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<BillDetailResponse> billDetailList) {
        this.billDetailList = billDetailList;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
