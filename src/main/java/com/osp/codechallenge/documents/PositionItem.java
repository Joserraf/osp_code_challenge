package com.osp.codechallenge.documents;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionItem {
    private String id;
    private String orderId;
}
