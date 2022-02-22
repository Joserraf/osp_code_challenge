package com.osp.codechallenge.controller;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.ShipmentDTO;
import com.osp.codechallenge.mapper.ShipmentsControllerMapper;
import com.osp.codechallenge.service.ShipmentsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@SecurityRequirement(name = "codechallenge")
@RequestMapping("/shipments")
public class ShipmentsController {

    private final ShipmentsService shipmentsService;
    private final ShipmentsControllerMapper mapper;

    public ShipmentsController(ShipmentsService shipmentsService, ShipmentsControllerMapper mapper) {
        this.shipmentsService = shipmentsService;
        this.mapper = mapper;
    }

    @PostMapping
    public Mono<Shipment> saveShipment(@Valid @RequestBody ShipmentDTO shipmentDTO){
        //TODO: ADD MESSAGE TO THE VALIDATION OF THE REQUEST
        Shipment shipment = mapper.toShipmentDocument(shipmentDTO);
        return shipmentsService.saveShipment(shipment);
    }

    @GetMapping
    public Flux<Shipment> getShipments(@RequestParam String id){
        return shipmentsService.getShipment(id);
    }
}
