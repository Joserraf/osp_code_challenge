package com.osp.codechallenge.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TrackingKey {

    @NotNull
    @NotEmpty
    private String carrier;

    @NotNull
    @NotEmpty
    private String trackingNumber;
}
