package com.osp.codechallenge.repositories;

import com.osp.codechallenge.documents.Shipment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ShipmentsRepository extends ReactiveMongoRepository<Shipment, String> {

    Flux<Shipment> findAllByPositionItemsOrderId(String orderId);
}
