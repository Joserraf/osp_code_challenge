package com.osp.codechallenge.dto;

import com.osp.codechallenge.documents.PositionItem;
import com.osp.codechallenge.documents.TrackingKey;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ShipmentDTO {

    @NotNull
    @Valid
    private TrackingKey trackingKey;
    private List<PositionItem> positionItems;

}
