package com.restaurant.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class BillRequest {

    @NotNull(message = "Client must not be empty")
    private Long clientId;

    @NotNull(message = "Waiter must not be empty")
    private Long waiterId;

    @NotNull(message = "Dining table must not be empty")
    private Long diningTableId;

    @NotNull(message = "Bill detail must not be empty")
    private List<BillDetailRequest> billDetailList;

    @NotNull(message = "Bill date is required")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate createdAt;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    public Long getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Long diningTableId) {
        this.diningTableId = diningTableId;
    }

    public List<BillDetailRequest> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<BillDetailRequest> billDetailList) {
        this.billDetailList = billDetailList;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
