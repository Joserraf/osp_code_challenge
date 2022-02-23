package com.osp.codechallenge.controller;

import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.PositionItemDTO;
import com.osp.codechallenge.dto.ShipmentDTO;
import com.osp.codechallenge.dto.TrackingKeyDTO;
import com.osp.codechallenge.mapper.ShipmentsControllerMapper;
import com.osp.codechallenge.service.ShipmentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller for the shipments management service
 */
@RestController
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
     * Method to obtain information from a shipment for the given carrier and tracking number
     * @param carrier the carrier name
     * @param trackingNumber the tracking number
     * @return a flux with the shipment information
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Get a shipment by its tracking number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the shipment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShipmentDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No shipments found.",
                    content = @Content) })
    public Flux<ShipmentDTO> getShipment(@RequestParam String carrier, @RequestParam String trackingNumber){
        log.debug("GET request /shipments?carrier={}&trackingNumber={}", carrier, trackingNumber);
        return mapper.toShipmentDTOFlux(shipmentsService.getShipment(carrier, trackingNumber))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No shipments found.")));
    }

    /**
     * Method to obtain information about every shipment with the given order id
     * @param orderId the order identifier of the shipment
     * @return a flux with the information of every shipment
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{orderId}")
    @Operation(summary = "Get shipment by its orderId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the shipment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShipmentDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No shipments found.",
                    content = @Content) })
    public Flux<ShipmentDTO> getShipments(@PathVariable String orderId){
        log.debug("GET request /shipments/{}", orderId);
        return mapper.toShipmentDTOFlux(shipmentsService.getShipments(orderId))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No shipments found.")));
    }

    /**
     * Method to save a shipment given the DTO
     * @param shipmentDTO the DTO object containing all the information about the shipment
     * @return the newly created shipment
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Shipment> saveShipment(@Validated @RequestBody ShipmentDTO shipmentDTO){
        /* Seems like is not possible to use the normal validation of the request body with @Valid, @NotNull
         * or custom constraints (for example) so I will do the validations in a method in the controller.
         * I have left the custom validator I had prepared in com.osp.codechallenge.configuration.validation,
         * just in case you want to check it.
         */
        validateRequest(shipmentDTO);
        log.debug("POST request /shipments requestBody: {}", shipmentDTO);
        Shipment shipment = mapper.toShipmentDocument(shipmentDTO);
        return shipmentsService.saveShipment(shipment);
    }

    private void validateRequest(ShipmentDTO shipmentDTO) {
        TrackingKeyDTO trackingKeyDTO = shipmentDTO.getTrackingKey();
        //Check that non of the tracking key fields are null or empty
        if(trackingKeyDTO == null
                || trackingKeyDTO.getTrackingNumber() == null || trackingKeyDTO.getTrackingNumber().isEmpty()
                || trackingKeyDTO.getCarrier() == null|| trackingKeyDTO.getCarrier().isEmpty()){
            //In case any of them are, return 400.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing tracking key data");
        }
        /* Creating a set to use it to find duplicates, a set accepts no duplicates so
         * if the operation add returns any false it means we have found a duplicate record
         * in the positions items.
         */
        Set<PositionItemDTO> items = new HashSet<>();
        List<PositionItemDTO> positionItems = shipmentDTO.getPositionItems();
        if(positionItems != null) {
            //Filtering the false results of the add operation, if there are any, that's duplicated data.
            Optional<PositionItemDTO> optionalPositionItem = positionItems.stream().filter(n -> !items.add(n)).findFirst();
            optionalPositionItem.ifPresent(positionItemDTO->{
                //In case there are, return 400.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The following position item data is repeated. ID: " + positionItemDTO.getId() + " OrderId: "+ positionItemDTO.getOrderId());
            });
        }
    }
}
