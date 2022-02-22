package com.osp.codechallenge.documents;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TrackingKey {

    private String carrier;
    private String tracking_number;
}
