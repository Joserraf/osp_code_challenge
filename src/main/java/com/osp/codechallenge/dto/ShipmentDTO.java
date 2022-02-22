package com.osp.codechallenge.dto;

import com.osp.codechallenge.documents.PositionItem;
import com.osp.codechallenge.documents.TrackingKey;
import lombok.Data;

import java.util.List;

@Data
public class ShipmentDTO {

    private TrackingKey trackingKey;
    private List<PositionItem> positionItems;

}
