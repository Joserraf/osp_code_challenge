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

    @Mapping(source = "trackingKey", target = "trackingKey")
    @Mapping(source = "positionItems", target = "positionItems")
    Shipment toShipmentDocument(ShipmentDTO dto);

    @InheritInverseConfiguration
    ShipmentDTO map(Shipment source);

    @Mapping(source = "orderId", target = "orderId")
    PositionItem toPositionItem(PositionItemDTO positionItemDTO);

    @InheritInverseConfiguration
    PositionItemDTO toPositionItemDTO(PositionItem positionItem);

    default Flux<ShipmentDTO> toShipmentDTOFlux(Flux<Shipment> flux) {
        return flux.map(this::map);
    }
}
