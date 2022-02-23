package com.osp.codechallenge.service;

import com.osp.codechallenge.documents.Shipment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Shipment service interface.
 */
public interface ShipmentsService {

    /**
     * Service method to get the shipment for the given tracking key.
     * @param carrier the carrier of the tracking key.
     * @param trackingNumber the tracking number of the tracking key.
     * @return flux cantaining the data of the shipment.
     */
    Flux<Shipment> getShipment(String carrier, String trackingNumber);

    /**
     * Service method to get every shipment that has the given order id
     * @param orderId the order id to search by.
     * @return flux with the data of every shipment.
     */
    Flux<Shipment> getShipments(String orderId);

    /**
     * Service method to save a shipment in the DDBB.
     * @param shipmentDTO the shipment DTO.
     * @return mono with the data that has been saved in the DDBB.
     */
    Mono<Shipment> saveShipment(Shipment shipmentDTO);
}
