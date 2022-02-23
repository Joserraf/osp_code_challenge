package com.osp.codechallenge.mapper;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.ShipmentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;

/**
 * Mapper between the DTO and Model classes for the shipments.
 */
@Mapper(componentModel="spring")
public interface ShipmentsControllerMapper {

    /**
     * Method to map from ShipmentDTO to Shipment model.
     * @param dto the shipment DTO.
     * @return the shipment model data.
     */
    @Mapping(source = "trackingKey", target = "trackingKey")
    @Mapping(source = "positionItems", target = "positionItems")
    Shipment toShipmentDocument(ShipmentDTO dto);

    /**
     * Method to map from Shipment model to ShipmentDTO.
     * @param document the shipment model data.
     * @return the shipment DTO.
     */
    @InheritInverseConfiguration
    ShipmentDTO toShipmentDTO(Shipment document);

    /**
     * Method to transform from a document flux to a DTO flux.
     * @param flux the flux containing the model data.
     * @return a flux with DTO data.
     */
    default Flux<ShipmentDTO> toShipmentDTOFlux(Flux<Shipment> flux) {
        return flux.map(this::toShipmentDTO);
    }
}
