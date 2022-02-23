package com.osp.codechallenge.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Position item DTO.
 */
@Data
@Builder
public class PositionItemDTO {
    private String id;
    private String orderId;
}
