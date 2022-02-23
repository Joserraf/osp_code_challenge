package com.osp.codechallenge.documents;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Shipment model for mongoDB.
 */
@Data
@Document(collection = "_shipments")
@Builder
public class Shipment {

    @Id
    private String id;
    private TrackingKey trackingKey;
    private List<PositionItem> positionItems;
}
