package com.osp.codechallenge.mapper;

import com.osp.codechallenge.documents.PositionItem;
import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.PositionItemDTO;
import com.osp.codechallenge.dto.ShipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;

import java.util.List;

@Mapper(componentModel="spring")
public interface ShipmentsControllerMapper {

    @Mapping(source = "trackingKey", target = "tracking_key")
    @Mapping(source = "positionItems", target = "position_items")
    @Mapping(source = "trackingKey.trackingNumber", target = "tracking_key.tracking_number")
    Shipment toShipmentDocument(ShipmentDTO dto);

    @Mapping(source = "orderId", target = "order_id")
    PositionItem toPositionItem(PositionItemDTO positionItemDTO);

    ShipmentDTO toShipmentDTO(Shipment shipment);

    default Flux<ShipmentDTO> toShipmentDTOFlux(Flux<Shipment> flux) {
        return flux.map(this::map);
    }

    ShipmentDTO map(Shipment source);
}
