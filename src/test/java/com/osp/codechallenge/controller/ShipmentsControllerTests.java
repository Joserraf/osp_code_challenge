package com.osp.codechallenge.controller;

import com.osp.codechallenge.configuration.SecurityConfig;
import com.osp.codechallenge.documents.PositionItem;
import com.osp.codechallenge.documents.Shipment;
import com.osp.codechallenge.dto.ShipmentDTO;
import com.osp.codechallenge.mapper.ShipmentsControllerMapperImpl;
import com.osp.codechallenge.repositories.ShipmentsRepository;
import com.osp.codechallenge.service.impl.ShipmentsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ShipmentsController.class)
@Import({ShipmentsServiceImpl.class, ShipmentsControllerMapperImpl.class, SecurityConfig.class})
public class ShipmentsControllerTests {

    @MockBean
    private ShipmentsRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void setup() {
        this.webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                // add Spring Security test Support
                .apply(springSecurity())
                .configureClient()
                .filter(basicAuthentication("user1", "user1Pass"))
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "user1Pass", roles="ADMIN")
    void testCreateShipment() {
        Shipment shipment = Shipment.builder().build();

        Mockito.when(repository.save(shipment)).thenReturn(Mono.just(shipment));

        webTestClient
                .mutateWith(csrf())
                .post()
                .uri("/shipments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ShipmentDTO()))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(repository, times(1)).save(shipment);
    }

    @Test
    @WithMockUser(username = "user1", password = "user1Pass", roles="ADMIN")
    void testGetShipment() {
        Shipment shipment = Shipment.builder().build();

        Mockito.when(repository.findAll(any(Example.class))).thenReturn(Flux.just(shipment));

        webTestClient
                .mutateWith(csrf())
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/shipments")
                                .queryParam("carrier", "MRW")
                                .queryParam("trackingNumber", "385868533623")
                                .build())
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(repository, times(1)).findAll(any(Example.class));
    }

    @Test
    @WithMockUser(username = "user1", password = "user1Pass", roles="ADMIN")
    void testGetShipments() {
        String orderId = "orderId";
        Shipment shipment = Shipment.builder().positionItems(Collections.singletonList(PositionItem.builder().orderId(orderId).build())).build();

        Mockito.when(repository.findAllByPositionItemsOrderId(orderId)).thenReturn(Flux.just(shipment));

        webTestClient
                .mutateWith(csrf())
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/shipments/"+orderId)
                                .build())
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(repository, times(1)).findAllByPositionItemsOrderId(orderId);
    }
}
