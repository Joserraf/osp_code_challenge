package com.osp.codechallenge.mapper;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.ShipmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ShipmentsControllerMapper {

    Shipment toShipmentDocument(ShipmentDTO dto);

}
