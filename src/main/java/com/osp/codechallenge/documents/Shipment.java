package com.osp.codechallenge.documents;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Shipments")
@Builder
public class Shipment {

    @Id
    private String id;
    private TrackingKey tracking_key;
    private List<PositionItem> position_items;
}
