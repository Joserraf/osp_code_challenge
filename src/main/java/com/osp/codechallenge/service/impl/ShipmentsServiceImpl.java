package com.osp.codechallenge.service.impl;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.documents.TrackingKey;
import com.osp.codechallenge.repositories.ShipmentsRepository;
import com.osp.codechallenge.service.ShipmentsService;
import org.springframework.data.domain.Example;
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
        return shipmentsRepository.save(shipmentDTO);
    }

    @Override
    public Flux<Shipment> getShipment(String carrier, String trackingNumber){
        return shipmentsRepository.findAll(Example.of(Shipment.builder().trackingKey(
                TrackingKey.builder().trackingNumber(trackingNumber).carrier(carrier).build()).build()));
    }

}
