package com.osp.codechallenge.documents;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TrackingKey {

    @NotNull
    private String carrier;

    @NotNull
    private String trackingNumber;
}
