package com.osp.codechallenge.service;

import com.osp.codechallenge.documents.Shipment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShipmentsService {
    Mono<Shipment> saveShipment(Shipment shipmentDTO);

    Flux<Shipment> getShipment(String shipmentDTO);
}
