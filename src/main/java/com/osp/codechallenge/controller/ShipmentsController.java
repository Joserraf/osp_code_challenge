package com.osp.codechallenge.controller;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.ShipmentDTO;
import com.osp.codechallenge.mapper.ShipmentsControllerMapper;
import com.osp.codechallenge.service.ShipmentsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Controller for the shipments management service
 */
@RestController
@SecurityRequirement(name = "codechallenge")
@RequestMapping("/shipments")
@Slf4j
public class ShipmentsController {

    private final ShipmentsService shipmentsService;
    private final ShipmentsControllerMapper mapper;

    /**
     * Shipments controller autowired constructor
     * @param shipmentsService the shipments service bean
     * @param mapper the mapper bean
     */
    public ShipmentsController(ShipmentsService shipmentsService, ShipmentsControllerMapper mapper) {
        this.shipmentsService = shipmentsService;
        this.mapper = mapper;
    }

    /**
     * Method to obtain information from a shipment for the given carrier and trackign number
     * @param carrier the carrier name
     * @param trackingNumber the tracking number
     * @return a flux with the shipment information
     */
    @GetMapping
    public Flux<ShipmentDTO> getShipment(@RequestParam String carrier, @RequestParam String trackingNumber){
        return mapper.toShipmentDTOFlux(shipmentsService.getShipment(carrier, trackingNumber));
    }

    /**
     * Method to obtain information about every shipment with the given order id
     * @param orderId the order identifier of the shipment
     * @return a flux with the information of every shipment
     */
    @GetMapping("/{orderId}")
    public Flux<ShipmentDTO> getShipments(@PathVariable String orderId){
        return mapper.toShipmentDTOFlux(shipmentsService.getShipments(orderId));
    }

    /**
     * Method to save a shipment given the DTO
     * @param shipmentDTO the DTO object containing all the information about the shipment
     * @return the newly created shipment
     */
    @PostMapping
    public Mono<Shipment> saveShipment(@Valid @RequestBody ShipmentDTO shipmentDTO){
        //TODO: ADD MESSAGE TO THE VALIDATION OF THE REQUEST
        //TODO: COMPLETE ALL THE EXCEPTIONS MANAGEMENTS AS AN "EXTRA", SO OUTSIDE THE MAIN POINTS
        //TODO: CHECK THE ERROR HANDLING OF THE MONGODB INDEXES
        //TODO: ADD LOGS
        //TODO: TESTING
        //TODO: OPEN-API
        //log.info("Authenticated user is: " + username);
        Shipment shipment = mapper.toShipmentDocument(shipmentDTO);
        return shipmentsService.saveShipment(shipment);
    }
}
