package com.osp.codechallenge.service.impl;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.repositories.ShipmentsRepository;
import com.osp.codechallenge.service.ShipmentsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShipmentsServiceImpl implements ShipmentsService {

    private final ShipmentsRepository shipmentsRepository;

    public ShipmentsServiceImpl(ShipmentsRepository shipmentsRepository) {
        this.shipmentsRepository = shipmentsRepository;
    }

    @Override
    public Mono<Shipment> saveShipment(Shipment shipmentDTO) {
        Mono<Shipment> shipmentsMono = shipmentsRepository.save(shipmentDTO);
        return shipmentsMono;
    }

    @Override
    public Flux<Shipment> getShipment(String id) {
        Flux<Shipment> shipmentsFlux = shipmentsRepository.findAll();
        return shipmentsFlux.publish();
    }



}
