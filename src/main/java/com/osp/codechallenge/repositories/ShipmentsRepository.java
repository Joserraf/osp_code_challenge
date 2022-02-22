package com.osp.codechallenge.repositories;

import com.osp.codechallenge.documents.Shipment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentsRepository extends ReactiveMongoRepository<Shipment, String> {
}
