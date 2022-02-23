package com.osp.codechallenge.repositories;

import com.osp.codechallenge.documents.Shipment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository class to connect to the mongoDB Shipment collection
 */
@Repository
public interface ShipmentsRepository extends ReactiveMongoRepository<Shipment, String> {

    /**
     * Method to find every shipment that has the given order id
     * @param orderId the order id to find by
     * @return flux with every shipment data
     */
    Flux<Shipment> findAllByPositionItemsOrderId(String orderId);
}
