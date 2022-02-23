package com.osp.codechallenge.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Tracking key DTO.
 */
@Builder
@Data
public class TrackingKeyDTO {

    private String carrier;

    private String trackingNumber;
}
