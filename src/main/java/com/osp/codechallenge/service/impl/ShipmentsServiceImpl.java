package com.osp.codechallenge.service.impl;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.repositories.ShipmentsRepository;
import com.osp.codechallenge.repositories.ShipmentsRepositoryNotReactive;
import com.osp.codechallenge.service.ShipmentsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShipmentsServiceImpl implements ShipmentsService {

    private final ShipmentsRepository shipmentsRepository;
    private final ShipmentsRepositoryNotReactive shipmentsRepositoryNotReactive;

    public ShipmentsServiceImpl(ShipmentsRepository shipmentsRepository, ShipmentsRepositoryNotReactive shipmentsRepositoryNotReactive) {
        this.shipmentsRepository = shipmentsRepository;
        this.shipmentsRepositoryNotReactive = shipmentsRepositoryNotReactive;
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
