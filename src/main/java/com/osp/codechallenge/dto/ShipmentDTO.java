package com.osp.codechallenge.dto;

import lombok.Data;

import java.util.List;

/**
 * Shipment DTO
 */
@Data
public class ShipmentDTO {

    private TrackingKeyDTO trackingKey;
    private List<PositionItemDTO> positionItems;

}
