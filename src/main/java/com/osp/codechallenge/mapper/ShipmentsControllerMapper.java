package com.osp.codechallenge.mapper;

import com.osp.codechallenge.documents.PositionItem;
import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.PositionItemDTO;
import com.osp.codechallenge.dto.ShipmentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;

@Mapper(componentModel="spring")
public interface ShipmentsControllerMapper {

    @Mapping(source = "trackingKey", target = "tracking_key")
    @Mapping(source = "positionItems", target = "position_items")
    @Mapping(source = "trackingKey.trackingNumber", target = "tracking_key.tracking_number")
    Shipment toShipmentDocument(ShipmentDTO dto);

    @InheritInverseConfiguration
    ShipmentDTO map(Shipment source);

    @Mapping(source = "orderId", target = "order_id")
    PositionItem toPositionItem(PositionItemDTO positionItemDTO);

    default Flux<ShipmentDTO> toShipmentDTOFlux(Flux<Shipment> flux) {
        return flux.map(this::map);
    }
}
