package com.osp.codechallenge.documents;

import lombok.Builder;
import lombok.Data;

/**
 * Position item model for mongoDB
 */
@Data
@Builder
public class PositionItem {
    private String id;
    private String orderId;
}
