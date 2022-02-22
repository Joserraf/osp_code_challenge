package com.osp.codechallenge.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TrackingKeyDTO {

    @NotNull
    @NotEmpty
    private String carrier;

    @NotNull
    @NotEmpty
    private String trackingNumber;
}
