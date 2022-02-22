package com.osp.codechallenge.dto;

import com.osp.codechallenge.configuration.validation.PositionItemConstraint;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ShipmentDTO {

    @NotNull
    @Valid
    private TrackingKeyDTO trackingKey;
    @PositionItemConstraint
    private List<PositionItemDTO> positionItems;

}
