package com.restaurant.mapper;

import com.restaurant.dto.response.BillDetailResponse;
import com.restaurant.entity.BillDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillDetailMapper {

    public BillDetailResponse entityToResponse(BillDetail billDetail) {
        BillDetailResponse detailResponse = new BillDetailResponse();
        detailResponse.setId(billDetail.getId());
        //detailResponse.setBill(billDetail.getBill());
        detailResponse.setCook(billDetail.getCook());
        detailResponse.setDish(billDetail.getDish());
        detailResponse.setPrice(billDetail.getPrice());
        return detailResponse;
    }

    public List<BillDetailResponse> entityListToResponseList(Iterable<BillDetail> billDetails) {
        List<BillDetailResponse> detailResponseList = new ArrayList<>();
        for (BillDetail billDetail : billDetails) {
            detailResponseList.add(this.entityToResponse(billDetail));
        }
        return  detailResponseList;
    }
}
