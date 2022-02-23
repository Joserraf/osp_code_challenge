package com.osp.codechallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Shipment DTO
 */
@Data
@Builder
public class ShipmentDTO {

    private TrackingKeyDTO trackingKey;
    private List<PositionItemDTO> positionItems;

}
