package com.osp.codechallenge.documents;

import lombok.Builder;
import lombok.Data;

/**
 * Tracking key model for mongoDB
 */
@Data
@Builder
public class TrackingKey {

    private String carrier;
    private String trackingNumber;
}
